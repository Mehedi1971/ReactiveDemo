package com.mahedi.reactivedemo.repository;

import com.mahedi.reactivedemo.model.Quotation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository extends ReactiveCrudRepository<Quotation, Long> {

}
