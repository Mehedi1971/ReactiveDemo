package com.mahedi.reactivedemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("global_values")
public class GlobalValue extends BaseModel{

//  @Id
//  private Long id;
  private String name;
  private String value;
}
