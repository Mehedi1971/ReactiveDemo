package com.mahedi.reactivedemo.model;

import com.mahedi.reactivedemo.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("payment")
public class Payment extends BaseModel{

//  @Id
//  private Long id;

  @Column("bill_id")
  private Long billId;

  private double amount;
  private PaymentMethod paymentMethod;
}
