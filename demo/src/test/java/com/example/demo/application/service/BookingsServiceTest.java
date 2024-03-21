package com.example.demo.application.service;

import com.example.demo.model.Bookings;
import com.example.demo.repository.BookingsRepository;
import com.example.demo.ui.dto.request.BookingRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class BookingsServiceTest {

    @Autowired
    private BookingsService bookingsService;

    @Autowired
    private BookingsRepository bookingsRepository;

    //@Autowired
    @InjectMocks
    NextSequenceService nextSequenceService;

    @Mock
    MongoOperations mongoOperations;

    @Test
    public void bookingContainerTest() {
        bookingsService.setNextSequenceService(nextSequenceService);
        Mockito.when(nextSequenceService.generateSequence("bookingsSequence")).thenReturn(1l);

        Mono<Bookings> bookingsRefMono = bookingsService.bookContainer(new BookingRequest(
                20,"DRY","India","Australia",
                40,"2021-10-12T13:53:09Z"));
        StepVerifier
                .create(bookingsRefMono)
                .consumeNextWith(bookingDoc -> {
                    assertEquals(bookingDoc.getBookingRef(), 1l);
                })
                .verifyComplete();
    }
}