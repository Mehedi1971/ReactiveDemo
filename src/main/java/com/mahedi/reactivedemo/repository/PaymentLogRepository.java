package com.mahedi.reactivedemo.repository;

import com.mahedi.reactivedemo.model.PaymentLog;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentLogRepository extends ReactiveCrudRepository<PaymentLog, Long> {

}
