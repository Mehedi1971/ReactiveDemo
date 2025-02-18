package com.mahedi.reactivedemo.repository;

import com.mahedi.reactivedemo.model.BillItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BillItemRepository extends ReactiveCrudRepository<BillItem, Long> {

  Flux<BillItem> findAllBy(Pageable pageable);
}
