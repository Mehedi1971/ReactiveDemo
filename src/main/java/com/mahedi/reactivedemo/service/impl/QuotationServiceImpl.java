package com.mahedi.reactivedemo.service.impl;

import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.model.Quotation;
import com.mahedi.reactivedemo.repository.QuotationRepository;
import com.mahedi.reactivedemo.service.QuotationService;
import com.mahedi.reactivedemo.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class QuotationServiceImpl implements QuotationService {

  private final QuotationRepository quotationRepository;

  @Override
  public Mono<Response> save(Quotation quotation) {
    return quotationRepository.save(quotation)
        .flatMap(savedQuotation -> ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, "created", savedQuotation));
  }
}
