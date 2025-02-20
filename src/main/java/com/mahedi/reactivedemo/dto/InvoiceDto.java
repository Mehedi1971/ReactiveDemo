package com.mahedi.reactivedemo.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {

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

}