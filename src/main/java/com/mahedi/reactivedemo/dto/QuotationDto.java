package com.mahedi.reactivedemo.dto;

import com.mahedi.reactivedemo.enums.EncounterType;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuotationDto extends BaseDto {

//  private Long id;

  private String billId;

  private LocalDateTime billDate;

  @Column("patient_id")
  private Long patientId;

  private EncounterType encounterType;
  private List<String> billItemIds;
}
