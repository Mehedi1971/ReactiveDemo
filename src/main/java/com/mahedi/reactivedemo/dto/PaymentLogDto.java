package com.mahedi.reactivedemo.dto;

import com.mahedi.reactivedemo.enums.PaymentStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentLogDto {

  private Long id;

  @Column("payment_id")
  private Long paymentId;

  private PaymentStatus paymentStatus;
  private String details;
  private LocalDateTime timestamp;
}
