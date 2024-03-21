package com.example.demo.ui.controller;

import com.example.demo.model.Bookings;
import com.example.demo.model.ContainerType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

@SpringBootTest
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ControllerTest {


        @Autowired
        private WebTestClient webTestClient;

        private Mono<Long> bookingRef;

        @Test
        @Order(0)
        public void createBooking() {
            /*BookingRequest bookingRequest = new BookingRequest(
                    20,"DRY","India","Australia",40,"2021-10-12T13:53:09Z");*/
            bookingRef = webTestClient.post()
                    .uri("/api/bookings/book")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(
                            new Bookings(20, ContainerType.DRY,"India","Australia",40,"2021-10-12T13:53:09Z")))
                    .exchange()
                    .expectStatus().is2xxSuccessful()
                    .returnResult(Long.class).getResponseBody().single();
            //bookingRef.
            //bookingMono.subscribe(bookingRefMonno -> this.bookingRef=bookingRefMonno);
            Assertions.assertNotNull(bookingRef);
        }
}
