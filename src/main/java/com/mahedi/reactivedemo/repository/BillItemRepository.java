package com.mahedi.reactivedemo.repository;

import com.mahedi.reactivedemo.model.BillItem;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillItemRepository extends ReactiveCrudRepository<BillItem, Long> {

}
