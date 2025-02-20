package com.mahedi.reactivedemo.mapper;

import com.mahedi.reactivedemo.dto.CategoryDto;
import com.mahedi.reactivedemo.model.Category;
import java.util.List;
import java.util.Objects;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class CategoryMapper {

  public Mono<CategoryDto> toDto(Category category) {
    return Mono.just(
        Objects.requireNonNull(CommonMapper.mapDtoToEntity(category, CategoryDto.class)));
  }

  public Mono<Category> toEntity(CategoryDto categoryDto) {
    return Mono.just(
        Objects.requireNonNull(CommonMapper.mapDtoToEntity(categoryDto, Category.class)));
  }

  public Mono<Category> toEntity(CategoryDto categoryDto, Category category) {
    return Mono.just(Objects.requireNonNull(CommonMapper.mapDtoToEntity(categoryDto, category)));
  }


  public Flux<CategoryDto> toDtoList(List<Category> categoryListList) {
    return Flux.fromIterable(categoryListList)
        .map(this::toDto)
        .flatMap(mono -> mono);
  }

  public Flux<Category> toEntityList(List<CategoryDto> dtoList) {
    return Flux.fromIterable(dtoList)
        .map(this::toEntity)
        .flatMap(mono -> mono);
  }
}
