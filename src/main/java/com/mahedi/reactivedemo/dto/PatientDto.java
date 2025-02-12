package com.mahedi.reactivedemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
  @JsonProperty("first_name")
  private String firstName;

  @JsonProperty("middle_name")
  private String middleName;

  @JsonProperty("last_name")
  private String lastName;

  @JsonProperty("full_name")
  private String fullName;

  private String nid;
  private String gender;
  private String brn;
  private String healthId;
  private String identifier;

  @JsonProperty("date_of_birth")
  private LocalDate dateOfBirth;

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
