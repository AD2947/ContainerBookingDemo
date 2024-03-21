package com.example.demo.application.service;

import com.example.demo.model.Bookings;
import com.example.demo.model.ContainerType;
import com.example.demo.repository.BookingsRepository;
import com.example.demo.ui.dto.request.BookingRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@Data
public class BookingsService {

    @Autowired
    private BookingsRepository bookingsRepository;

    @Autowired
    private NextSequenceService nextSequenceService;

    /*public Mono<Long> bookContainer(BookingRequest bookingRequest) {

        return bookingsRepository.save(mapToBookings(bookingRequest)).
                flatMap(booking -> Mono.just(booking.getBookingRef()));
    }*/

    /**
     * Save the booking in db
     * @param bookingRequest - Booking request from UI
     * @return - Mono of the booking
     */
    public Mono<Bookings> bookContainer(BookingRequest bookingRequest) {

        return bookingsRepository.save(mapToBookings(bookingRequest));
    }

    /**
     * Convert to Booking document from booking request
     * @param bookingRequest - Booking request from UI
     * @return Bookings document
     */
    public  Bookings mapToBookings(BookingRequest bookingRequest){
        return new Bookings(nextSequenceService.generateSequence(Bookings.SEQUENCE_NAME),
                bookingRequest.getContainerSize(),
                bookingRequest.getContainerType() == "DRY" ? ContainerType.DRY : ContainerType.REEFER,
                bookingRequest.getOrigin(),
                bookingRequest.getDestination(),
                bookingRequest.getQuantity(),
                bookingRequest.getTimestamp()
        );
    }

    public Flux<Bookings> getAllBookings(){
        return bookingsRepository.findAll();
    }

    public Flux<Bookings> getBookingsByContainerType(String containerType){
        //the values are validated in the request before reaching here
        return bookingsRepository.findByContainerType(containerType == "DRY" ? ContainerType.DRY : ContainerType.REEFER);
    }

}
