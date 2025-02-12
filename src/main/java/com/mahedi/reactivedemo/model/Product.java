package com.mahedi.reactivedemo.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("product")
public class Product {
  @Id
  private Long id;
  private String name;
  private double price;

  @Column("category_id")
  private Long categoryId;
}
