package com.mahedi.reactivedemo.controller;

import com.mahedi.reactivedemo.dto.PatientDto;
import com.mahedi.reactivedemo.dto.Response;
import com.mahedi.reactivedemo.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin("/*")
@RequiredArgsConstructor
public class PatientController {

  private final PatientService patientService;


  @PostMapping
  public Mono<Response> createPatient(@RequestBody PatientDto patientDto) {
    return patientService.save(patientDto);
  }

  @GetMapping("/{id}")
  public Mono<Response> getPatient(@PathVariable Long id) {
    return patientService.getPatientById(id);
  }

  @GetMapping
  public Mono<Response> getAllPatients() {
    return patientService.getAllPatients();
  }


  @PutMapping("/{id}")
  public Mono<Response> updatePatient(@PathVariable Long id,
      @RequestBody PatientDto patientDto) {
    return patientService.updatePatient(id, patientDto);
  }


  @DeleteMapping("/{id}")
  public Mono<Response> deletePatient(@PathVariable Long id) {
    return patientService.deletePatient(id);
  }

}