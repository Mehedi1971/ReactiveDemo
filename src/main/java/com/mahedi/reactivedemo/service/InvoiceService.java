package com.mahedi.reactivedemo.service;

import com.mahedi.reactivedemo.dto.BillConformationDto;
import com.mahedi.reactivedemo.dto.InvoiceDto;
import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.model.Invoice;
import java.time.LocalDate;
import java.util.Optional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InvoiceService {

  Mono<Response> saveInvoice(InvoiceDto invoiceDto);

  Mono<Response> getInvoices(int page, int size);

  Flux<Invoice> saveBillItems(BillConformationDto billDto);

  Flux<Invoice> filterInvoices(
      Optional<String> patientId,
      Optional<String> billId,
      Optional<Long> categoryId,
      Optional<LocalDate> startDate,
      Optional<LocalDate> endDate,
      Optional<Double> minPrice,
      Optional<Double> maxPrice
  );
}
