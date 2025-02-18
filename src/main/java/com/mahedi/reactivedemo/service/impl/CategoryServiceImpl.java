package com.mahedi.reactivedemo.service.impl;

import com.mahedi.reactivedemo.dto.CategoryDto;
import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.mapper.CategoryMapper;
import com.mahedi.reactivedemo.model.Category;
import com.mahedi.reactivedemo.repository.CategoryRepository;
import com.mahedi.reactivedemo.service.CategoryService;
import com.mahedi.reactivedemo.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  @Override
  public Mono<Response> saveCategory(CategoryDto categoryDto) {
    Mono<Category> category = categoryMapper.toEntity(categoryDto);
    return category.flatMap(categoryRepository::save)
        .flatMap(savedCategory -> ResponseBuilder.getSuccessResponse(
            HttpStatus.CREATED,
            "Category Created Successfully",
            savedCategory));
  }

  @Override
  public Mono<Response> getCategories(int page, int size) {
    return categoryRepository.findAllBy(
            PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")))
        .collectList()
        .flatMap(categories -> categories.isEmpty()
            ? ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found")
            : categoryMapper.toDtoList(categories)
                .collectList()
                .flatMap(categoryDtoList -> ResponseBuilder.getSuccessResponse(
                    HttpStatus.OK, "Categories retrieved", categoryDtoList, categoryDtoList.size()))
        );
  }

  @Override
  public Mono<Response> getCategory(Long id) {
    return categoryRepository.findById(id)
        .flatMap(category -> categoryMapper.toDto(category)
            .flatMap(
                categoryDto -> ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Category found",
                    categoryDto)))
        .switchIfEmpty(ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found"));
  }

  @Override
  public Mono<Response> updateCategory(Long id, CategoryDto categoryDto) {
    return categoryRepository.findById(id)
        .flatMap(existingCategory -> categoryMapper.toEntity(categoryDto, existingCategory)
            .flatMap(categoryRepository::save)
            .flatMap(savedCategory ->
                ResponseBuilder.getSuccessResponse(
                    HttpStatus.OK, "Category has been updated!", savedCategory
                )
            )
        )
        .switchIfEmpty(ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found"));
  }

  @Override
  public Mono<Response> deleteCategory(Long id) {
    return categoryRepository.findById(id)
        .flatMap(existingCategory ->
            categoryRepository.deleteById(id)
                .then(ResponseBuilder.getSuccessResponse(HttpStatus.OK,
                    "Category has been deleted!")))
        .switchIfEmpty(ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found"));
  }
}
