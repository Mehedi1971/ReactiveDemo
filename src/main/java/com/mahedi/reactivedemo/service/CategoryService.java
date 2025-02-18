package com.mahedi.reactivedemo.service;

import com.mahedi.reactivedemo.dto.CategoryDto;
import com.mahedi.reactivedemo.dto.Response;
import reactor.core.publisher.Mono;

public interface CategoryService {

  Mono<Response> saveCategory(CategoryDto categoryDto);

  Mono<Response> getCategories(int page, int size);

  Mono<Response> getCategory(Long id);

  Mono<Response> updateCategory(Long id, CategoryDto categoryDto);

  Mono<Response> deleteCategory(Long id);
}
