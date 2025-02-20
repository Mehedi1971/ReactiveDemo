package com.mahedi.reactivedemo.mapper;


import com.mahedi.reactivedemo.dto.InvoiceDto;
import com.mahedi.reactivedemo.model.Invoice;
import java.util.List;
import java.util.Objects;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class InvoiceMapper {

  public Mono<InvoiceDto> toDto(Invoice invoice) {
    return Mono.just(
        Objects.requireNonNull(CommonMapper.mapDtoToEntity(invoice, InvoiceDto.class)));
  }

  public Mono<Invoice> toEntity(InvoiceDto invoiceDto) {
    return Mono.just(
        Objects.requireNonNull(CommonMapper.mapDtoToEntity(invoiceDto, Invoice.class)));
  }

  public Mono<Invoice> toEntity(InvoiceDto invoiceDto, Invoice invoice) {
    return Mono.just(Objects.requireNonNull(CommonMapper.mapDtoToEntity(invoiceDto, invoice)));
  }


  public Flux<InvoiceDto> toDtoList(List<Invoice> invoiceListList) {
    return Flux.fromIterable(invoiceListList)
        .map(this::toDto)
        .flatMap(mono -> mono);
  }

  public Flux<Invoice> toEntityList(List<InvoiceDto> dtoList) {
    return Flux.fromIterable(dtoList)
        .map(this::toEntity)
        .flatMap(mono -> mono);
  }
}
