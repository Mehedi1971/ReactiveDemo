package com.mahedi.reactivedemo.service.impl;

import com.mahedi.reactivedemo.dto.PatientDto;
import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.mapper.PatientMapper;
import com.mahedi.reactivedemo.model.Patient;
import com.mahedi.reactivedemo.repository.PatientRepository;
import com.mahedi.reactivedemo.service.PatientService;
import com.mahedi.reactivedemo.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

  private final PatientRepository patientRepository;
  private final PatientMapper patientMapper;


  @Override
  public Mono<Response> addPatient(PatientDto patientDto) {
    Mono<Patient> patient = patientMapper.toEntity(patientDto);
    return patient.flatMap(patientRepository::save)
        .flatMap(savedPatient -> ResponseBuilder.getSuccessResponse(
            HttpStatus.CREATED,
            "Created Patient Successfully",
            savedPatient));
  }

  @Override
  public Mono<Response> findPatient(int page, int size) {
    return patientRepository.findAllBy(
            PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")))
        .collectList()
        .flatMap(patients -> patients.isEmpty()
            ? ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found")
            : patientMapper.toDtoList(patients)
                .collectList()
                .flatMap(patientDtoList -> ResponseBuilder.getSuccessResponse(
                    HttpStatus.OK, "Patients retrieved", patientDtoList, patientDtoList.size()))
        );

  }


  @Override
  public Mono<Response> findPatient(String patientId) {
    return patientRepository.findByPatientId(patientId)
        .flatMap(patient -> patientMapper.toDto(patient)
            .flatMap(
                patientDto -> ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Patient found",
                    patientDto)))
        .switchIfEmpty(ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found"));
  }

  @Override
  public Mono<Response> updatePatient(String id, PatientDto patientDto) {
    return patientRepository.findByPatientId(id)
        .flatMap(existingPatient -> patientMapper.toEntity(patientDto, existingPatient)
            .flatMap(patientRepository::save)
            .flatMap(savedPatient ->
                ResponseBuilder.getSuccessResponse(
                    HttpStatus.OK, "Patient has been updated!", savedPatient
                )
            )
        )
        .switchIfEmpty(ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found"));
  }

  @Override
  public Mono<Response> deletePatient(String patientId) {
    return patientRepository.findByPatientId(patientId)
        .flatMap(existingPatient ->
            patientRepository.deleteByPatientId(patientId)
                .then(ResponseBuilder.getSuccessResponse(HttpStatus.OK,
                    "Patient has been deleted!")))
        .switchIfEmpty(ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found"));
  }

}
