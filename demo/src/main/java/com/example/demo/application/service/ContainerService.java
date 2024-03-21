package com.example.demo.application.service;

import com.example.demo.repository.ContainerRepository;
import com.example.demo.ui.dto.request.ContainerBookingRequest;
import com.example.demo.ui.dto.response.AvailableSpaceResponse;
import com.example.demo.ui.dto.response.AvailablilityResponse;
import com.example.demo.ui.exception.ServiceException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

import static com.example.demo.utils.Constants.CHECK_AVAILABLE_URI;

@Slf4j
@Data
@Service
//@PropertySource("classpath:application.yml")
public class ContainerService {

    @Autowired
    private ContainerRepository containerRepository;

    private WebClient webClient;

    /**
     * Check availability of the container with configurations from UI
     * @param containerBookingRequest
     * @return Mono of AvaialabilityResponse
     */
    public Mono<AvailablilityResponse> checkAvailability(ContainerBookingRequest containerBookingRequest) {
        Mono<AvailableSpaceResponse> availableSpaceResponseMono =
                makeExternalCall(containerBookingRequest);
        return availableSpaceResponseMono.flatMap(space -> {
            if(space.getAvailableSpace() == 0){
                return Mono.just(new AvailablilityResponse(false));
            }
            else{
                return Mono.just(new AvailablilityResponse(true));
            }
        });
    }

    /**
     * Make call to the external api for getting the information on availability
     * @param containerBookingRequest
     * @return - Mono of AvailabilitySpaceResponse
     */
    public Mono<AvailableSpaceResponse> makeExternalCall(ContainerBookingRequest containerBookingRequest){
        return getWebClient().method(HttpMethod.GET).
                uri(getUri()).body(Mono.just(containerBookingRequest), ContainerBookingRequest.class).
                //bodyValue(BodyInserters.fromValue(newEmployee)).
                        accept(MediaType.APPLICATION_JSON).retrieve().
                onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new ServiceException("Server error", response.statusCode().value()))).
                bodyToMono(AvailableSpaceResponse.class).
                retryWhen(Retry.backoff(3, Duration.ofSeconds(5)).
                        filter(throwable -> throwable instanceof ServiceException));
    }

    public String getUri(){
        return CHECK_AVAILABLE_URI;
    }

    public WebClient getWebClient(){
        return WebClient.create();
    }

}
