package com.mahedi.reactivedemo.service;

import com.mahedi.reactivedemo.dto.BillItemDto;
import com.mahedi.reactivedemo.dto.CategoryDto;
import com.mahedi.reactivedemo.dto.Response;
import reactor.core.publisher.Mono;

public interface BillItemService {

  Mono<Response> saveBillItem(BillItemDto billItemDto);

  Mono<Response> getBillItems(int page, int size);

  Mono<Response> getBillItem(Long id);

  Mono<Response> updateBillItem(Long id, BillItemDto billItemDto);

  Mono<Response> deleteBillItem(Long id);
}
