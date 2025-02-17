package com.mahedi.reactivedemo.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "encounter")
public class Encounter {

  @Id
  private Long id;
  private String visitType;  // outpatient, inpatient, emergency
  private String encounterType;  // REG, OPD, IPD
  private String encounterLocation;
  @Column("patient_id")
  private Long patientId;
}
