package com.example.demo.repository;

import com.example.demo.model.Bookings;
import com.example.demo.model.ContainerType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookingsRepository extends ReactiveMongoRepository<Bookings, Long> {
    Mono<Bookings> save(Bookings bookings);
    Mono<Bookings> findById(Long Id);
    Flux<Bookings> findAll();
    Flux<Bookings> findByContainerType(ContainerType containerType);

}
