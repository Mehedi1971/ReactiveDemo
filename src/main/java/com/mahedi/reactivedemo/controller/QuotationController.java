package com.mahedi.reactivedemo.controller;

import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.model.Quotation;
import com.mahedi.reactivedemo.service.QuotationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
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
  public Mono<Response> createPatient(@RequestBody Quotation quotation) {
    return quotationService.save(quotation);
  }
}
