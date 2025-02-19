package com.mahedi.reactivedemo.mapper;

import com.mahedi.reactivedemo.dto.PaymentDto;
import com.mahedi.reactivedemo.model.Payment;
import java.util.List;
import java.util.Objects;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class PaymentMapper {

  public Mono<PaymentDto> toDto(Payment payment) {
    return Mono.just(
        Objects.requireNonNull(CommonMapper.mapDtoToEntity(payment, PaymentDto.class)));
  }

  public Mono<Payment> toEntity(PaymentDto paymentDto) {
    return Mono.just(
        Objects.requireNonNull(CommonMapper.mapDtoToEntity(paymentDto, Payment.class)));
  }

  public Mono<Payment> toEntity(PaymentDto paymentDto, Payment payment) {
    return Mono.just(Objects.requireNonNull(CommonMapper.mapDtoToEntity(paymentDto, payment)));
  }


  public Flux<PaymentDto> toDtoList(List<Payment> paymentList) {
    return Flux.fromIterable(paymentList)
        .map(this::toDto)
        .flatMap(mono -> mono);
  }

  public Flux<Payment> toEntityList(List<PaymentDto> dtoList) {
    return Flux.fromIterable(dtoList)
        .map(this::toEntity)
        .flatMap(mono -> mono);
  }
}
