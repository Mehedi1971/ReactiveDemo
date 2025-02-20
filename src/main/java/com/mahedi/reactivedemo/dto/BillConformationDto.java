package com.mahedi.reactivedemo.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillConformationDto {

  private String patientId;
  private String billId;
  private LocalDate date;
  private List<BillConformationItemDto> items;
}
