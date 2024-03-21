package com.example.demo.repository;

import com.example.demo.model.Container;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ContainerRepository extends ReactiveMongoRepository<Container, String> {
    Mono<Container> findById(String Id);
    Mono<Container> save(Container container);
    Flux<Container> findAll();
}
