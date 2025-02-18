package com.mahedi.reactivedemo.service.impl;

import com.mahedi.reactivedemo.dto.QuotationDto;
import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.mapper.QuotationMapper;
import com.mahedi.reactivedemo.model.GlobalValue;
import com.mahedi.reactivedemo.model.Quotation;
import com.mahedi.reactivedemo.repository.GlobalValueRepository;
import com.mahedi.reactivedemo.repository.QuotationRepository;
import com.mahedi.reactivedemo.service.QuotationService;
import com.mahedi.reactivedemo.util.ResponseBuilder;
import java.time.Year;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class QuotationServiceImpl implements QuotationService {

  private static final String BILL_ID_NAME = "Bill_ID";
  private static final String BILL_ID_PREFIX = "INV-";
  private static final String INITIAL_INDEX = "000001";


  private final QuotationRepository quotationRepository;
  private final GlobalValueRepository globalValueRepository;
  private final QuotationMapper quotationMapper;

  @Override
  public Mono<Response> save(QuotationDto quotationDto) {
    return generateBillId()
        .flatMap(billId -> {
          return quotationMapper.toEntity(quotationDto)
              .map(quotation -> {
                quotation.setBillId(billId);
                quotation.setBillItemIdsFromList(
                    quotationDto.getBillItemIds());
                return quotation;
              });
        })
        .flatMap(quotationRepository::save)
        .flatMap(savedQuotation -> ResponseBuilder.getSuccessResponse(
            HttpStatus.CREATED, "Created Quotation Successfully", savedQuotation))
        .defaultIfEmpty(ResponseBuilder.getFailureResponse(
            HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"));
  }

  public Mono<String> generateBillId() {
    return globalValueRepository.findByName(BILL_ID_NAME)
        .flatMap(existingGlobalValue -> {
          String currentValue = existingGlobalValue.getValue();
          String[] parts = currentValue.split("-");
          String existingYear = parts[1];
          String currentIndex = parts[2];

          String currentYear = String.valueOf(Year.now().getValue());

          // Check if the year has changed
          if (!existingYear.equals(currentYear)) {
            // If the year has changed, reset the index to 000001
            existingGlobalValue.setValue(BILL_ID_PREFIX + currentYear + "-" + INITIAL_INDEX);
          } else {
            // If the year is the same, increment the index
            int index = Integer.parseInt(currentIndex); // Convert to integer
            String newIndex = String.format("%06d", index + 1); // Increment and format as 6-digit string
            existingGlobalValue.setValue(BILL_ID_PREFIX + currentYear + "-" + newIndex);
          }
          return globalValueRepository.save(existingGlobalValue)
              .map(GlobalValue::getValue);
        })
        .switchIfEmpty(Mono.defer(() -> {
          GlobalValue globalValue = new GlobalValue();
          globalValue.setName(BILL_ID_NAME);
          String yearPrefix = String.valueOf(Year.now().getValue());
          globalValue.setValue(BILL_ID_PREFIX + yearPrefix + "-" + INITIAL_INDEX);
          return globalValueRepository.save(globalValue)
              .map(GlobalValue::getValue);
        }));
  }

  @Override
  public Mono<Response> getQutationById(Long id) {
    Mono<Quotation> quotationData = quotationRepository.findById(id);
    return quotationData.flatMap(quotation ->
//            quotationMapper.toDto(quotation)
//                .flatMap(quotationDto ->
                ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Patient found", quotation)
//                )
        )
        .defaultIfEmpty(ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found"));

  }

  @Override
  public Mono<Response> getAllQutation() {
    return quotationRepository.findAll()
        .collectList()
        .flatMap(quotations -> {
          if (quotations.isEmpty()) {
            return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found");
          }
          return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Quotations retrieved",
              quotations, quotations.size());
        });
  }


  @Override
  public Mono<Response> updateQutation(Long id, QuotationDto quotationDto) {
    return quotationRepository.findById(id)
        .flatMap(existingQuotation ->
            quotationMapper.toEntity(quotationDto, existingQuotation)
        )
        .flatMap(quotationRepository::save)
        .flatMap(savedQuotation ->
            ResponseBuilder.getSuccessResponse(
                HttpStatus.OK, "Quotation has been updated!", savedQuotation)
        )
        .defaultIfEmpty(ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found"));

  }

  @Override
  public Mono<Response> deleteQutation(Long id) {
    return quotationRepository.findById(id)
        .flatMap(existingPatient ->
            quotationRepository.deleteById(id)
                .then(ResponseBuilder.getSuccessResponse(HttpStatus.OK,
                    "Quotation has been deleted!")))
        .defaultIfEmpty(ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found"));

  }
}
