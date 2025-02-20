package com.mahedi.reactivedemo.service.impl;

import com.mahedi.reactivedemo.dto.PatientDto;
import com.mahedi.reactivedemo.dto.PaymentDto;
import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.enums.PaymentMethod;
import com.mahedi.reactivedemo.enums.PaymentStatus;
import com.mahedi.reactivedemo.mapper.PaymentMapper;
import com.mahedi.reactivedemo.model.Patient;
import com.mahedi.reactivedemo.model.Payment;
import com.mahedi.reactivedemo.model.PaymentLog;
import com.mahedi.reactivedemo.repository.PaymentLogRepository;
import com.mahedi.reactivedemo.repository.PaymentRepository;
import com.mahedi.reactivedemo.service.PaymentService;
import com.mahedi.reactivedemo.util.ResponseBuilder;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
  private final PaymentRepository paymentRepository;
  private final PaymentLogRepository paymentLogRepository;
  private final PaymentMapper paymentMapper;


  @Override
  public Mono<Response> createPayment(PaymentDto paymentDto) {
    //          payment.setAuditData();
    return paymentMapper.toEntity(paymentDto)
        .flatMap(paymentRepository::save)
        .flatMap(savedPayment -> {
          PaymentStatus status = (savedPayment.getPaymentMethod() == PaymentMethod.CASH)
              ? PaymentStatus.COMPLETED
              : PaymentStatus.PENDING;

          PaymentLog paymentLog = new PaymentLog(
              savedPayment.getId(),
              status,
              (savedPayment.getPaymentMethod() == PaymentMethod.CASH)
                  ? "Cash payment completed."
                  : "Payment initiated.",
              LocalDateTime.now()
          );

          return paymentLogRepository.save(paymentLog)
              .thenReturn(savedPayment);
        })
        .flatMap(savedPayment -> ResponseBuilder.getSuccessResponse(
            HttpStatus.CREATED, "Payment created successfully", savedPayment));
  }
  @Override
  public Mono<Response> getAllPayments() {
    return paymentRepository.findAll()
        .collectList()
        .flatMap(payments -> {
          if (payments.isEmpty()) {
            return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found");
          }
          return paymentMapper.toDtoList(payments)
              .collectList()
              .flatMap(paymentDtos ->
                  ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Payments retrieved",
                      paymentDtos, paymentDtos.size())
              );
        });
  }

  @Override
  public Mono<Response> getPaymentById(Long id) {
    Mono<Payment> paymentData = paymentRepository.findById(id);
    return paymentData.flatMap(payment ->
            paymentMapper.toDto(payment)
                .flatMap(paymentDto ->
                    ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Payment found", paymentDto)
                )
        )
        .defaultIfEmpty(ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found"));

  }
  @Override
  public Mono<Response> updatePayment(Long id, PaymentDto paymentDto) {
    return paymentRepository.findById(id)
        .flatMap(existingPayment ->
            paymentMapper.toEntity(paymentDto, existingPayment)
        )
        .flatMap(paymentRepository::save)
        .flatMap(savedPayment ->
            ResponseBuilder.getSuccessResponse(
                HttpStatus.OK, "Payment has been updated!", savedPayment)
        )
        .defaultIfEmpty(ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found"));

  }

//  @Override
//  public Mono<Response> deletePayment(Long id) {
//    return paymentRepository.findById(id)
//        .flatMap(existingPayment ->
//            paymentRepository.deleteById(id)
//                .then(ResponseBuilder.getSuccessResponse(HttpStatus.OK,
//                    "Payment has been deleted!")))
//        .defaultIfEmpty(ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found"));
//  }
}
