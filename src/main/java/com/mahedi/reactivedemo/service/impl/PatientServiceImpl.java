package com.mahedi.reactivedemo.service.impl;

import com.mahedi.reactivedemo.model.Patient;
import com.mahedi.reactivedemo.repository.PatientRepository;
import com.mahedi.reactivedemo.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
//@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

  private final PatientRepository patientRepository;

  public PatientServiceImpl(PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
  }

  @Override
  public Mono<Patient> save(Patient patient) {
    return patientRepository.save(patient);
  }
}
