package com.mahedi.reactivedemo.controller;

import com.mahedi.reactivedemo.dto.CategoryDto;
import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping
  public Mono<Response> createCategory(@RequestBody CategoryDto categoryDto) {
    return categoryService.saveCategory(categoryDto);
  }

  @GetMapping("/{id}")
  public Mono<Response> getCategory(@PathVariable Long id) {
    return categoryService.getCategory(id);
  }

  @GetMapping
  public Mono<Response> getCategories(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "5") int size) {
    return categoryService.getCategories(page, size);
  }


  @PutMapping("/{id}")
  public Mono<Response> updateCategory(@PathVariable Long id,
      @RequestBody CategoryDto categoryDto) {
    return categoryService.updateCategory(id, categoryDto);
  }


  @DeleteMapping("/{id}")
  public Mono<Response> deleteCategory(@PathVariable Long id) {
    return categoryService.deleteCategory(id);
  }
}
