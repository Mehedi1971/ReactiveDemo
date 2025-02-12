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
@Table(name = "bill_item")
public class BillItem {

  @Id
  private Long id;

  @Column("bill_id")
  private Long billId;

  @Column("product_id")
  private Long productId;

  private int quantity;
  private double price;
}
