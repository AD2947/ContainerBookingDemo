package com.example.demo.application.service;

import com.example.demo.ui.controller.ContainerBookingController;
import com.example.demo.ui.dto.request.ContainerBookingRequest;
import com.example.demo.ui.dto.response.AvailableSpaceResponse;
import com.example.demo.ui.dto.response.AvailablilityResponse;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@ExtendWith(SpringExtension.class)
//@RunWith(SpringRunner.class)

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration//(locations={"file:src/main/resources/application.yml"})
//@Import(Constants.class)
//@TestPropertySource("src/main/resources/application.yml")
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContainerServiceTest {
    //@Autowired
    //private WebClient webClient;
    /*@Mock
    private WebClient.RequestBodyUriSpec uriSpec;
    @Mock
    private WebClient.RequestBodyUriSpec headerSpec;
*/
    @Mock
    ContainerService containerService;

    @Autowired
    ContainerBookingController containerBookingController;

    //@Autowired
    //Environment environment;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void checkContainer(){


        /*WebClient.RequestBodyUriSpec bodySpec = mock(WebClient.RequestBodyUriSpec.class);
        WebClient.ResponseSpec response = mock(WebClient.ResponseSpec.class);

        when(webClient.post()).thenReturn(uriSpec);
        when(uriSpec.uri(CHECK_AVAILABLE_URI)).thenReturn(headerSpec);
        //doReturn(bodySpec).when(headerSpec).bodyValue(body);
        when(bodySpec.retrieve()).thenReturn(response);*/

        //ContainerBookingRequest containerBookingRequest = new ContainerBookingRequest();
        AvailableSpaceResponse availableSpaceResponse = new AvailableSpaceResponse(60);
        AvailablilityResponse availablilityResponse = new AvailablilityResponse(true);
        //WebClient webClient = containerService.getWebClient();
        /*Mockito.when(webClient.method(HttpMethod.GET).uri(any(),"http://maersk:com/api/bookings/checkAvailabl").body(Mono.just(containerBookingRequest),ContainerBookingRequest.class)
                .exchange()
                .flatMap(clientResponse ->
                        clientResponse.bodyToMono(AvailableSpaceResponse.class)).block()).thenReturn(availableSpaceResponse);*/
        //Mockito.when(containerService.makeExternalCall(containerBookingRequest)).thenReturn(Mono.just(availableSpaceResponse));
        //Mockito.when(containerService.getUri()).thenReturn("http://maersk:com/api/bookings/checkAvailabl");
        //Mockito.when
        ContainerBookingRequest containerBookingRequest = new ContainerBookingRequest(20, "DRY","India",
                "Australia", 40);
        when(containerService.makeExternalCall(containerBookingRequest)).thenReturn(Mono.just(availableSpaceResponse));
        //when(containerService.makeExternalCall(containerBookingRequest)).thenReturn(Mono.just(availableSpaceResponse));
        Mono<AvailablilityResponse> availablilityResponseMono = Mono.just(availablilityResponse);
        when(containerService.checkAvailability(containerBookingRequest)).thenCallRealMethod();

        availablilityResponseMono = containerService.checkAvailability(containerBookingRequest);//).thenReturn(Mono.just(availablilityResponse));

        StepVerifier
                .create(availablilityResponseMono)
                .consumeNextWith(availablilityResp -> {
                    assertEquals(availablilityResp.getAvailable(), true);
                })
                .verifyComplete();

    }
}
