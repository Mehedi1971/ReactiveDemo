package com.mahedi.reactivedemo.service;

import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.model.Quotation;
import reactor.core.publisher.Mono;

public interface QuotationService {

  Mono<Response> save(Quotation quotation);
}
