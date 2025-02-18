package com.mahedi.reactivedemo.service;

import com.mahedi.reactivedemo.dto.PatientDto;
import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.model.Patient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PatientService {

  Mono<Response> addPatient(PatientDto patientDto);

  Mono<Response> findPatient(int page, int size);

  Mono<Response> findPatient(String patientId);

  Mono<Response> updatePatient(String id, PatientDto patientDto);

  Mono<Response> deletePatient(String patientId);
}
