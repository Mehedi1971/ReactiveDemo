package com.mahedi.reactivedemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bill_item")
public class BillItem extends BaseModel{

//  @Id
//  private Long id;  // Primary Key

  private String name;

  @Column("category_id")
  private Long categoryId;

  private int quantity;
  private double price;
}
