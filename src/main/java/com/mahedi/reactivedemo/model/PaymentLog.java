package com.mahedi.reactivedemo.model;

import com.mahedi.reactivedemo.enums.PaymentStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("payment_log")
public class PaymentLog extends BaseModel {
//  @Id
//  private Long id;

  @Column("payment_id")
  private Long paymentId;

  private PaymentStatus paymentStatus;
  private String details;
  private LocalDateTime timestamp;
}
