package com.example.demo.repository;

import com.example.demo.model.Bookings;
import com.example.demo.model.ContainerType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class BookingRepositoryTest {
    @Autowired
    private BookingsRepository bookingsRepository;

    @Test
    public void saveBooking() {
        Bookings booking = new Bookings(1l,20, ContainerType.DRY,"India","Australia",40,"2021-10-12T13:53:09Z");
        Publisher<Bookings> setup = bookingsRepository.deleteAll().thenMany(bookingsRepository.save(booking));
        StepVerifier
                .create(setup)
                .expectNextCount(1)
                .verifyComplete();
    }
}
