package com.mahedi.reactivedemo.mapper;

import com.mahedi.reactivedemo.dto.QuotationDto;
import com.mahedi.reactivedemo.model.Quotation;
import java.util.List;
import java.util.Objects;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class QuotationMapper {

  public Mono<QuotationDto> toDto(Quotation quotation) {
    return Mono.just(
        Objects.requireNonNull(CommonMapper.mapDtoToEntity(quotation, QuotationDto.class)));
  }

  public Mono<Quotation> toEntity(QuotationDto quotationDto) {
    return Mono.just(
        Objects.requireNonNull(CommonMapper.mapDtoToEntity(quotationDto, Quotation.class)));
  }

  public Mono<Quotation> toEntity(QuotationDto quotationDto, Quotation quotation) {
    return Mono.just(Objects.requireNonNull(CommonMapper.mapDtoToEntity(quotationDto, quotation)));
  }


  public Flux<QuotationDto> toDtoList(List<Quotation> quotationList) {
    return Flux.fromIterable(quotationList)
        .flatMap(this::toDto);
  }


  public Flux<Quotation> toEntityList(List<QuotationDto> dtoList) {
    return Flux.fromIterable(dtoList)
        .map(this::toEntity)
        .flatMap(mono -> mono);
  }
}
