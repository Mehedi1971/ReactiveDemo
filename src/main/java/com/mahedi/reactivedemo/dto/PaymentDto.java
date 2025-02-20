package com.mahedi.reactivedemo.dto;

import com.mahedi.reactivedemo.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto extends BaseDto {

//  private Long id;

  @Column("bill_id")
  private Long billId;

  private double amount;
  private PaymentMethod paymentMethod;
}
