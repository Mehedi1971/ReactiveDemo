package com.mahedi.reactivedemo.controller;

import com.mahedi.reactivedemo.model.Patient;
import com.mahedi.reactivedemo.service.PatientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/patients")
//@RequiredArgsConstructor
public class PatientController {

  private final PatientService patientService;

  public PatientController(PatientService patientService) {
    this.patientService = patientService;
  }

  @PostMapping
  public Mono<Patient> createPatient(@RequestBody Patient patient) {
    return patientService.save(patient);
  }
}
