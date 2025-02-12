package com.mahedi.reactivedemo.service;

import com.mahedi.reactivedemo.model.Patient;
import reactor.core.publisher.Mono;

public interface PatientService {

  Mono<Patient> save(Patient patient);
}
