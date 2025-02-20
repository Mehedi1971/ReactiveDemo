package com.mahedi.reactivedemo.repository;

import com.mahedi.reactivedemo.model.Invoice;
import java.time.LocalDate;
import java.util.Optional;
import reactor.core.publisher.Flux;

public interface InvoiceDynamicFilterRepository {
  Flux<Invoice> findByDynamicFilters(
      Optional<String> patientId,
      Optional<String> billId,
      Optional<Long> categoryId,
      Optional<LocalDate> startDate,
      Optional<LocalDate> endDate);
}
