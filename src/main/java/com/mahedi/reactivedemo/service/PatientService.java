package com.mahedi.reactivedemo.service;

import com.mahedi.reactivedemo.dto.PatientDto;
import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.model.Patient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PatientService {

  Mono<Response> save(PatientDto patientDto);

  Mono<Response> getAllPatients();

  Mono<Response> getPatientById(Long id);

  Mono<Response> updatePatient(Long id, PatientDto patientDto);

  Mono<Response>deletePatient(Long id);
}
