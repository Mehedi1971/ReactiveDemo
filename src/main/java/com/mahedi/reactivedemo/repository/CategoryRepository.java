package com.mahedi.reactivedemo.repository;

import com.mahedi.reactivedemo.model.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CategoryRepository extends ReactiveCrudRepository<Category, Long> {

  Flux<Category> findAllBy(Pageable pageable);
}
