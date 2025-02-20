package com.mahedi.reactivedemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BillItemDto {

  private Long id;
  private String name;
  private Long categoryId;
  private int quantity;
  private double price;
}