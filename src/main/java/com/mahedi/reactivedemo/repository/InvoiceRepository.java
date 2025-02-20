package com.mahedi.reactivedemo.repository;

import com.mahedi.reactivedemo.model.Invoice;
import java.time.LocalDate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface InvoiceRepository  extends ReactiveCrudRepository<Invoice, Long>, InvoiceDynamicFilterRepository {

  Flux<Invoice> findAllBy(Pageable pageable);

  Flux<Invoice> findByPatientId(String patientId);

  Flux<Invoice> findByBillId(String billId);

  Flux<Invoice> findByCategoryId(Long categoryId);

  Flux<Invoice> findByDateBetween(LocalDate startDate, LocalDate endDate);

  Flux<Invoice> findByTotalPriceBetween(Double minPrice, Double maxPrice);

  Flux<Invoice> findByCategoryIdAndDateBetween(Long categoryId, LocalDate startDate, LocalDate endDate);

}
