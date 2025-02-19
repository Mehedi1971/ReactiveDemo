package com.mahedi.reactivedemo.model;

import com.mahedi.reactivedemo.enums.EncounterType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "quotation")
public class Quotation extends BaseModel{

//  @Id
//  private Long id;

  private String billId;

  private LocalDateTime billDate;

  @Column("patient_id")
  private Long patientId;
  private EncounterType encounterType;


  @Column("bill_item_ids")
  private String billItemIds;

//  public List<Long> getBillItemIdsAsList() {
//    return billItemIds == null ? List.of() : Stream.of(billItemIds.split(","))
//        .map(Long::parseLong)
//        .collect(Collectors.toList());
//  }

  public void setBillItemIdsFromList(List<String> billItemIdsList) {
    this.billItemIds = billItemIdsList.stream()
        .map(String::valueOf)
        .collect(Collectors.joining(","));
  }
}
