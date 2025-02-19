package com.mahedi.reactivedemo.service;

import com.mahedi.reactivedemo.dto.PaymentDto;
import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.model.Payment;
import reactor.core.publisher.Mono;

public interface PaymentService {

  Mono<Response> createPayment(PaymentDto paymentDto);

  Mono<Response> getPaymentById(Long id);

  Mono<Response> getAllPayments();

  Mono<Response> updatePayment(Long id, PaymentDto paymentDto);

//  Mono<Response> deletePayment(Long );
}
