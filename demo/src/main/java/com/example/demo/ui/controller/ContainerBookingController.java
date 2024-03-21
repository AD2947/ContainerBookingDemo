package com.example.demo.ui.controller;

import com.example.demo.application.service.BookingsService;
import com.example.demo.application.service.ContainerService;
import com.example.demo.model.Bookings;
import com.example.demo.model.ContainerType;
import com.example.demo.ui.dto.request.BookingRequest;
import com.example.demo.ui.dto.request.ContainerBookingRequest;
import com.example.demo.ui.dto.response.AvailablilityResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * REST API endpoint for container booking related operations
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/bookings")
public class ContainerBookingController {
    //log = org.slf4j.LoggerFactory.getLogger(ContainerBookingController.class);

    private final ContainerService containerService;

    private final BookingsService bookingsService;

    public ContainerBookingController(
            final ContainerService containerService, final BookingsService bookingsService) {
        this.containerService = containerService;
        this.bookingsService = bookingsService;
    }

    @Operation(summary = "Check for container availability")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Container available",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AvailablilityResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Container not found",
                    content = @Content) })
    @PostMapping(value = "/checkAvailability", produces="application/json", consumes = "application/json")
    public ResponseEntity<Mono<AvailablilityResponse>> check(
           @RequestBody @Valid ContainerBookingRequest containerBookingRequest){

        log.debug("Check container availability: {}", containerBookingRequest);
        Mono<AvailablilityResponse> availablilityResponseMono = containerService.checkAvailability(containerBookingRequest);
        HttpStatus status = ( availablilityResponseMono != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(availablilityResponseMono, status);
    }

    @Operation(summary = "Book the available container")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Container booked",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AvailablilityResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content) })
    @PostMapping(value = "/bookContainer", produces = "application/json", consumes="application/json")
    public Mono<Long> book(
            @RequestBody @Valid BookingRequest bookingRequest){
        log.debug("Submit book request: {}", bookingRequest);

        return bookingsService.bookContainer(bookingRequest).
                flatMap(booking -> Mono.just(booking.getBookingRef()));
    }

    @GetMapping(value = "/allBookings")
    public Flux<Bookings> getAllBookings(){

        return bookingsService.getAllBookings();
    }

    @GetMapping(value = "/bookingsByType/{containerType}")
    public ResponseEntity<Flux<Bookings>> getBookingsByContainerType(@Parameter(description = "ContainerType of Bookings")
                                                                         @PathVariable @Valid String containerType){
        if(!StringUtils.equals(containerType, ContainerType.DRY.toString())
                && !StringUtils.equals(containerType, ContainerType.REEFER.toString())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookingsService.getBookingsByContainerType(containerType));
    }


}

