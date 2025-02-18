package com.mahedi.reactivedemo.controller;

import com.mahedi.reactivedemo.dto.BillItemDto;
import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.service.BillItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bill-items")
@RequiredArgsConstructor
public class BillItemController {

  private final BillItemService billItemService;

  @PostMapping
  public Mono<Response> createBillItem(@RequestBody BillItemDto billItemDto) {
    return billItemService.saveBillItem(billItemDto);
  }

  @GetMapping("/{id}")
  public Mono<Response> getBillItem(@PathVariable Long id) {
    return billItemService.getBillItem(id);
  }

  @GetMapping
  public Mono<Response> getBillItems(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "5") int size) {
    return billItemService.getBillItems(page, size);
  }


  @PutMapping("/{id}")
  public Mono<Response> updateBillItem(@PathVariable Long id,
      @RequestBody BillItemDto billItemDto) {
    return billItemService.updateBillItem(id, billItemDto);
  }


  @DeleteMapping("/{id}")
  public Mono<Response> deleteBillItem(@PathVariable Long id) {
    return billItemService.deleteBillItem(id);
  }
}
