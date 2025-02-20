package com.mahedi.reactivedemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillConformationItemDto {
  private Long itemId;//foreign key from bill_item(id)
  private String itemName;
  private Long categoryId;
  private String categoryName;
  private Double unitPrice;
  private Integer quantity;
  private Double totalPrice;
}
