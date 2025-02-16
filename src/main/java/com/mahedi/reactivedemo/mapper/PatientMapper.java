package com.mahedi.reactivedemo.mapper;

import com.mahedi.reactivedemo.dto.PatientDto;
import com.mahedi.reactivedemo.model.Patient;
import java.util.List;
import java.util.Objects;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class PatientMapper {
  public Mono<PatientDto> toDto(Patient patient) {
    return Mono.just(Objects.requireNonNull(CommonMapper.mapDtoToEntity(patient, PatientDto.class)));
  }

  public Mono<Patient> toEntity(PatientDto patientDto) {
    return Mono.just(Objects.requireNonNull(CommonMapper.mapDtoToEntity(patientDto, Patient.class)));
  }

  public Mono<Patient> toEntity(PatientDto patientDto, Patient patient) {
    return Mono.just(Objects.requireNonNull(CommonMapper.mapDtoToEntity(patientDto, patient)));
  }


  public Flux<PatientDto> toDtoList(List<Patient> patientList) {
    return Flux.fromIterable(patientList)
        .map(this::toDto)
        .flatMap(mono -> mono);
  }

  public Flux<Patient> toEntityList(List<PatientDto> dtoList) {
    return Flux.fromIterable(dtoList)
        .map(this::toEntity)
        .flatMap(mono -> mono);
  }
}
