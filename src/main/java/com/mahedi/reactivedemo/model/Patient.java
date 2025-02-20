package com.mahedi.reactivedemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
@Table(name = "patient_info")
public class Patient extends BaseModel{

//  @Id
//  private Long id;

  @JsonProperty("patient_id")
  private String patientId;

//  @Column("first_name")
  @JsonProperty("first_name")
  private String firstName;

//  @Column("middle_name")
  @JsonProperty("middle_name")
  private String middleName;

//  @Column("last_name")
  @JsonProperty("last_name")
  private String lastName;

//  @Column("full_name")
  @JsonProperty("full_name")
  private String fullName;

  private String nid;
  private String gender;
  private String brn;
  private String healthId;
  private String identifier;

//  @Column("date_of_birth")
  @JsonProperty("date_of_birth")
  private LocalDate dateOfBirth;

//  @Column("contact_number")
  @JsonProperty("contact_number")
  private String contactNumber;
  private String address;
  private String division;
  private String district;
  private String upazila;

  @JsonProperty("address_line")
  private String addressLine;

  @JsonProperty("father_name_english")
  private String fatherNameEnglish;

  @JsonProperty("father_name_bengali")
  private String fatherNameBengali;

  @JsonProperty("mother_name_english")
  private String motherNameEnglish;

  @JsonProperty("mother_name_bengali")
  private String motherNameBengali;

  @JsonProperty("relative_name")
  private String relativeName;

  @JsonProperty("spouse_name")
  private String spouseName;

  @JsonProperty("emergency_contact_number")
  private String emergencyContactNumber;

  private String religion;
  private String profession;
  private String designation;

  private LocalDateTime date;

}
