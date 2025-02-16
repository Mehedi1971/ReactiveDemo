package com.mahedi.reactivedemo.mapper;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CommonMapper {

  private static final ModelMapper modelMapper = new ModelMapper();

  public static <T, U> T mapEntityToDto(U entity, Class<T> dtoClass) {
    try {
      T dto = dtoClass.getDeclaredConstructor().newInstance();

      modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
      modelMapper.map(entity, dto);
      return dto;
    } catch (Exception e) {
      // Handle exception appropriately
      e.printStackTrace();
      return null;
    }
  }

  public static <T, U> U mapDtoToEntity(T dto, Class<U> entityClass) {
    try {
      U entity = entityClass.getDeclaredConstructor().newInstance();
      modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
      modelMapper.map(dto, entity);
      return entity;
    } catch (Exception e) {
      // Handle exception appropriately
      e.printStackTrace();
      return null;
    }
  }

  public static <T, U> U mapDtoToEntity(T dto, U entity) {
    try {
      modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
      modelMapper.map(dto, entity);
      return entity;
    } catch (Exception e) {
      // Handle exception appropriately
      e.printStackTrace();
      return null;
    }
  }
}
