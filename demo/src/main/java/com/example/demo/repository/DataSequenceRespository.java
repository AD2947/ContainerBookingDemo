package com.example.demo.repository;

import com.example.demo.model.DatabaseSequence;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSequenceRespository extends ReactiveMongoRepository<DatabaseSequence, String> {

}
