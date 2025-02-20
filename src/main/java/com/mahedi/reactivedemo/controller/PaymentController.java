package com.mahedi.reactivedemo.controller;

import com.mahedi.reactivedemo.dto.PatientDto;
import com.mahedi.reactivedemo.dto.PaymentDto;
import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.model.Payment;
import com.mahedi.reactivedemo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/payments")
@CrossOrigin("/*")
@RequiredArgsConstructor
public class PaymentController {
  private final PaymentService paymentService;

  @PostMapping
  public Mono<Response> createPayment(@RequestBody PaymentDto paymentDto) {
    return paymentService.createPayment(paymentDto);
  }

  @GetMapping("/{id}")
  public Mono<Response> getPaymentById(@PathVariable Long id) {
    return paymentService.getPaymentById(id);
  }

  @GetMapping
  public Mono<Response> getAllPayments() {
    return paymentService.getAllPayments();
  }


  @PutMapping("/{id}")
  public Mono<Response> updatePayment(@PathVariable Long id,
      @RequestBody PaymentDto paymentDto) {
    return paymentService.updatePayment(id, paymentDto);
  }


//  @DeleteMapping("/{id}")
//  public Mono<Response> deletePayment(@PathVariable Long id) {
//    return paymentService.deletePayment(id);
//  }
}
