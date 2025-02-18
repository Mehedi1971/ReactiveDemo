package com.mahedi.reactivedemo.repository;

import com.mahedi.reactivedemo.model.GlobalValue;
import java.util.Optional;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface GlobalValueRepository extends ReactiveCrudRepository<GlobalValue, Long> {

  Mono<GlobalValue> findByName(String billId);
}
