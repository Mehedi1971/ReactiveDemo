package com.mahedi.reactivedemo.service.impl;

import com.mahedi.reactivedemo.dto.PatientDto;
import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.mapper.PatientMapper;
import com.mahedi.reactivedemo.model.Patient;
import com.mahedi.reactivedemo.repository.PatientRepository;
import com.mahedi.reactivedemo.service.PatientService;
import com.mahedi.reactivedemo.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

  private final PatientRepository patientRepository;
  private final PatientMapper patientMapper;


  @Override
  public Mono<Response> save(PatientDto patientDto) {
    Mono<Patient> patient = patientMapper.toEntity(patientDto);
    return patient.flatMap(patientRepository::save)
        .flatMap(savedPatient -> ResponseBuilder.getSuccessResponse(
            HttpStatus.CREATED,
            "Created Patient Successfully",
            savedPatient))
        .defaultIfEmpty(ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"));
  }

  @Override
  public Mono<Response> getAllPatients() {
    return patientRepository.findAll()
        .collectList()
        .flatMap(patients -> {
          if (patients.isEmpty()) {
            return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found");
          }
          return patientMapper.toDtoList(patients)
              .collectList()
              .flatMap(patientDtos ->
                  ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Patients retrieved",
                      patientDtos, patientDtos.size())
              );
        });
  }

  @Override
  public Mono<Response> getPatientById(Long id) {
    Mono<Patient> patientData = patientRepository.findById(id);
    return patientData.flatMap(patient ->
            patientMapper.toDto(patient)
                .flatMap(patientDto ->
                    ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Patient found", patientDto)
                )
        )
        .defaultIfEmpty(ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found"));

  }

  @Override
  public Mono<Response> updatePatient(Long id, PatientDto patientDto) {
    return patientRepository.findById(id)
        .flatMap(existingPatient ->
            patientMapper.toEntity(patientDto, existingPatient)
        )
        .flatMap(patientRepository::save)
        .flatMap(savedPatient ->
            ResponseBuilder.getSuccessResponse(
                HttpStatus.OK, "Patient has been updated!", savedPatient)
        )
        .defaultIfEmpty(ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found"));

  }

  @Override
  public Mono<Response> deletePatient(Long id) {
    return patientRepository.findById(id)
        .flatMap(existingPatient ->
            patientRepository.deleteById(id)
                .then(ResponseBuilder.getSuccessResponse(HttpStatus.OK,
                    "Patient has been deleted!")))
        .defaultIfEmpty(ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No data found"));
  }


}
