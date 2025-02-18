package com.mahedi.reactivedemo.service;

import com.mahedi.reactivedemo.dto.QuotationDto;
import com.mahedi.reactivedemo.dto.Response;
import reactor.core.publisher.Mono;

public interface QuotationService {

  Mono<Response> save(QuotationDto quotationDto);

  Mono<Response> getQutationById(Long id);

  Mono<Response> getAllQutation();

  Mono<Response> updateQutation(Long id, QuotationDto quotationDto);

  Mono<Response> deleteQutation(Long id);
}
