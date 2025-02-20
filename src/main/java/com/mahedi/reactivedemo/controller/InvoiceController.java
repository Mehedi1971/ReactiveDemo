package com.mahedi.reactivedemo.controller;

import com.mahedi.reactivedemo.dto.BillConformationDto;
import com.mahedi.reactivedemo.dto.InvoiceDto;
import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.model.Invoice;
import com.mahedi.reactivedemo.service.InvoiceService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

  private final InvoiceService invoiceService;

  @PostMapping
  public Mono<Response> createCategory(@RequestBody InvoiceDto invoiceDto) {
    return invoiceService.saveInvoice(invoiceDto);
  }

//  @GetMapping("/{id}")
//  public Mono<Response> getCategory(@PathVariable Long id) {
//    return invoiceService.getInvoices(id);
//  }

  @GetMapping
  public Mono<Response> getCategories(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "5") int size) {
    return invoiceService.getInvoices(page, size);
  }
  @PostMapping("/save")
  Flux<Invoice> saveBillItems(@RequestBody BillConformationDto billDto){
    return invoiceService.saveBillItems(billDto);
  }

  @GetMapping("/filter")
  public Flux<Invoice> filterInvoices(
      @RequestParam Optional<String> patientId,
      @RequestParam Optional<String> billId,
      @RequestParam Optional<Long> categoryId,
      @RequestParam Optional<LocalDate> startDate,
      @RequestParam Optional<LocalDate> endDate,
      @RequestParam Optional<Double> minPrice,
      @RequestParam Optional<Double> maxPrice
  ) {
    return invoiceService.filterInvoices(patientId, billId, categoryId, startDate, endDate, minPrice, maxPrice);
  }

}
