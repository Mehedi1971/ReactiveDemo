package com.mahedi.reactivedemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bill_item")
public class BillItem {

  @Id
  private Long id;

  private String name;

  @Column("category_id")
  private Long categoryId;

  private int quantity;
  private double price;
}
