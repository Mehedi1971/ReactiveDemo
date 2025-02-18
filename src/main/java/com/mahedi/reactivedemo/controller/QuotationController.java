package com.mahedi.reactivedemo.controller;

import com.mahedi.reactivedemo.dto.PatientDto;
import com.mahedi.reactivedemo.dto.QuotationDto;
import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.model.Quotation;
import com.mahedi.reactivedemo.service.QuotationService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/quotation")
@RequiredArgsConstructor
public class QuotationController {

  private final QuotationService quotationService;


  @PostMapping
  public Mono<Response> createPatient(@RequestBody QuotationDto quotationDto) {
    return quotationService.save(quotationDto);
  }

  @GetMapping("/{id}")
  public Mono<Response> getQutationById(@PathVariable Long id) {
    return quotationService.getQutationById(id);
  }

  @GetMapping
  public Mono<Response> getAllPatients() {
    return quotationService.getAllQutation();
  }


  @PutMapping("/{id}")
  public Mono<Response> updatePatient(@PathVariable Long id,
      @RequestBody QuotationDto quotationDto) {
    return quotationService.updateQutation(id, quotationDto);
  }


  @DeleteMapping("/{id}")
  public Mono<Response> deletePatient(@PathVariable Long id) {
    return quotationService.deleteQutation(id);
  }


}
