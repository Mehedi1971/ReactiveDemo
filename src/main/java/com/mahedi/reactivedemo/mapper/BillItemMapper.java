package com.mahedi.reactivedemo.mapper;


import com.mahedi.reactivedemo.dto.BillItemDto;
import com.mahedi.reactivedemo.model.BillItem;
import java.util.List;
import java.util.Objects;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class BillItemMapper {

  public Mono<BillItemDto> toDto(BillItem billItem) {
    return Mono.just(
        Objects.requireNonNull(CommonMapper.mapDtoToEntity(billItem, BillItemDto.class)));
  }

  public Mono<BillItem> toEntity(BillItemDto billItemDto) {
    return Mono.just(
        Objects.requireNonNull(CommonMapper.mapDtoToEntity(billItemDto, BillItem.class)));
  }

  public Mono<BillItem> toEntity(BillItemDto billItemDto, BillItem billItem) {
    return Mono.just(Objects.requireNonNull(CommonMapper.mapDtoToEntity(billItemDto, billItem)));
  }


  public Flux<BillItemDto> toDtoList(List<BillItem> categoryListList) {
    return Flux.fromIterable(categoryListList)
        .map(this::toDto)
        .flatMap(mono -> mono);
  }

  public Flux<BillItem> toEntityList(List<BillItemDto> dtoList) {
    return Flux.fromIterable(dtoList)
        .map(this::toEntity)
        .flatMap(mono -> mono);
  }
}
