package com.mahedi.reactivedemo.repository;

import com.mahedi.reactivedemo.model.Invoice;
import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class InvoiceDynamicFilterRepositoryImpl implements InvoiceDynamicFilterRepository {

  private final R2dbcEntityTemplate template;

  @Override
  public Flux<Invoice> findByDynamicFilters(
      Optional<String> patientId,
      Optional<String> billId,
      Optional<Long> categoryId,
      Optional<LocalDate> startDate,
      Optional<LocalDate> endDate) {

    Criteria criteria = Criteria.empty();

    if (patientId.isPresent()) {
      criteria = criteria.and("patient_id").is(patientId.get());
    }
    if (billId.isPresent()) {
      criteria = criteria.and("bill_id").is(billId.get());
    }
    if (categoryId.isPresent()) {
      criteria = criteria.and("category_id").is(categoryId.get());
    }

    if (startDate.isPresent() && endDate.isPresent()) {
      criteria = criteria.and("date").greaterThanOrEquals(startDate.get())
          .and("date").lessThanOrEquals(endDate.get());
    } else if (startDate.isPresent()) {
      criteria = criteria.and("date").greaterThanOrEquals(startDate.get());
    } else if (endDate.isPresent()) {
      criteria = criteria.and("date").lessThanOrEquals(endDate.get());
    }

    Query query = Query.query(criteria);
    return template.select(query, Invoice.class);
  }


}
