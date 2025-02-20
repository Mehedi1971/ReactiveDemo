package com.mahedi.reactivedemo.service.impl;

import com.mahedi.reactivedemo.dto.BillConformationDto;
import com.mahedi.reactivedemo.dto.InvoiceDto;
import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.mapper.InvoiceMapper;
import com.mahedi.reactivedemo.model.Invoice;
import com.mahedi.reactivedemo.repository.InvoiceRepository;
import com.mahedi.reactivedemo.service.InvoiceService;
import com.mahedi.reactivedemo.util.ResponseBuilder;
import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

  private final InvoiceRepository invoiceRepository;
  private final InvoiceMapper invoiceMapper;

  @Override
  public Mono<Response> saveInvoice(InvoiceDto invoiceDto) {
    Mono<Invoice> invoice = invoiceMapper.toEntity(invoiceDto);
    System.out.println(invoice);
    return invoice.flatMap(invoiceRepository::save)
        .flatMap(savedCategory -> ResponseBuilder.getSuccessResponse(
            HttpStatus.CREATED,
            "Invoice Created Successfully",
            savedCategory));
  }

  @Override
  public Mono<Response> getInvoices(int page, int size) {
    return invoiceRepository.findAllBy(
            PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")))
        .collectList()
        .flatMap(categories -> categories.isEmpty()
            ? ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found")
            : invoiceMapper.toDtoList(categories)
                .collectList()
                .flatMap(categoryDtoList -> ResponseBuilder.getSuccessResponse(
                    HttpStatus.OK, "Invoices retrieved", categoryDtoList, categoryDtoList.size()))
        );
  }
  @Override
  public Flux<Invoice> saveBillItems(BillConformationDto billDto) {
    Flux<Invoice> billItems = Flux.fromIterable(billDto.getItems())
        .map(item -> Invoice.builder()
            .patientId(billDto.getPatientId())
            .billId(billDto.getBillId())
            .date(billDto.getDate())
            .itemId(item.getItemId())
            .itemName(item.getItemName())
            .categoryId(item.getCategoryId())
            .categoryName(item.getCategoryName())
            .unitPrice(item.getUnitPrice())
            .quantity(item.getQuantity())
            .totalPrice(item.getTotalPrice())
            .build()
        );

    return invoiceRepository.saveAll(billItems);
  }
  @Override
  public Flux<Invoice> filterInvoices(
      Optional<String> patientId,
      Optional<String> billId,
      Optional<Long> categoryId,
      Optional<LocalDate> startDate,
      Optional<LocalDate> endDate,
      Optional<Double> minPrice,
      Optional<Double> maxPrice) {

    return invoiceRepository.findByDynamicFilters(patientId, billId, categoryId, startDate, endDate);
  }

//    return invoiceRepository.findAll()
//        .filter(invoice -> patientId.map(id -> invoice.getPatientId().equals(id)).orElse(true))
//        .filter(invoice -> billId.map(id -> invoice.getBillId().equals(id)).orElse(true))
//        .filter(invoice -> categoryId.map(id -> invoice.getCategoryId().equals(id)).orElse(true))
//        .filter(invoice -> startDate.map(date -> invoice.getDate().isAfter(date) || invoice.getDate().isEqual(date)).orElse(true))
//        .filter(invoice -> endDate.map(date -> invoice.getDate().isBefore(date) || invoice.getDate().isEqual(date)).orElse(true))
//        .filter(invoice -> minPrice.map(price -> invoice.getTotalPrice() >= price).orElse(true))
//        .filter(invoice -> maxPrice.map(price -> invoice.getTotalPrice() <= price).orElse(true));
//  }
}
