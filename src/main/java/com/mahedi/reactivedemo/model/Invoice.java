package com.mahedi.reactivedemo.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("invoice")
public class Invoice {
  @Id
  private Long id;
  private String patientId;//foreign key from patient(patientId)
  private String billId;
  private LocalDate date;
  private Long itemId;//foreign key from bill_item(id)
  private String itemName;
  private Long categoryId;
  private String categoryName;
  private Double unitPrice;
  private Integer quantity;
  private Double totalPrice;

//  public Invoice(String patientId, String billId, LocalDate date, Long itemId, String itemName, Long categoryId, String categoryName, Double unitPrice, Integer quantity,
//      Double totalPrice) {
//  }
}
