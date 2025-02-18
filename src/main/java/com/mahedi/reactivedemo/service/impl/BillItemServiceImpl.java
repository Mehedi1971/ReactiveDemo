package com.mahedi.reactivedemo.service.impl;

import com.mahedi.reactivedemo.dto.BillItemDto;
import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.mapper.BillItemMapper;
import com.mahedi.reactivedemo.model.BillItem;
import com.mahedi.reactivedemo.repository.BillItemRepository;
import com.mahedi.reactivedemo.service.BillItemService;
import com.mahedi.reactivedemo.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BillItemServiceImpl implements BillItemService {

  private final BillItemRepository billItemRepository;
  private final BillItemMapper billItemMapper;

  @Override
  public Mono<Response> saveBillItem(BillItemDto billItemDto) {
    Mono<BillItem> billItem = billItemMapper.toEntity(billItemDto);
    return billItem.flatMap(billItemRepository::save)
        .flatMap(savedBillItem -> ResponseBuilder.getSuccessResponse(
            HttpStatus.CREATED,
            "BillItem Created Successfully",
            savedBillItem));
  }

  @Override
  public Mono<Response> getBillItems(int page, int size) {
    return billItemRepository.findAllBy(
            PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")))
        .collectList()
        .flatMap(billItems -> billItems.isEmpty()
            ? ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found")
            : billItemMapper.toDtoList(billItems)
                .collectList()
                .flatMap(billItemsDtoList -> ResponseBuilder.getSuccessResponse(
                    HttpStatus.OK, "BillItems retrieved", billItemsDtoList,
                    billItemsDtoList.size()))
        );
  }

  @Override
  public Mono<Response> getBillItem(Long id) {
    return billItemRepository.findById(id)
        .flatMap(billItem -> billItemMapper.toDto(billItem)
            .flatMap(
                billItemDto -> ResponseBuilder.getSuccessResponse(HttpStatus.OK, "BillItem found",
                    billItemDto)))
        .switchIfEmpty(ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found"));
  }

  @Override
  public Mono<Response> updateBillItem(Long id, BillItemDto billItemDto) {
    return billItemRepository.findById(id)
        .flatMap(existingBillItem -> billItemMapper.toEntity(billItemDto, existingBillItem)
            .flatMap(billItemRepository::save)
            .flatMap(savedBillItem ->
                ResponseBuilder.getSuccessResponse(
                    HttpStatus.OK, "BillItem has been updated!", savedBillItem
                )
            )
        )
        .switchIfEmpty(ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found"));
  }

  @Override
  public Mono<Response> deleteBillItem(Long id) {
    return billItemRepository.findById(id)
        .flatMap(existingBillItem ->
            billItemRepository.deleteById(id)
                .then(ResponseBuilder.getSuccessResponse(HttpStatus.OK,
                    "BillItem has been deleted!")))
        .switchIfEmpty(ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found"));
  }
}
