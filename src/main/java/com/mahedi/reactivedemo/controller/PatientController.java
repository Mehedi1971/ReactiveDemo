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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin("/*")
@RequiredArgsConstructor
public class PatientController {

  private final PatientService patientService;

  @PostMapping
  public Mono<Response> addNewPatient(@RequestBody PatientDto patientDto) {
    return patientService.addPatient(patientDto);
  }

  @GetMapping
  public Mono<Response> getPatient(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
    return patientService.findPatient(page, size);
  }

  @GetMapping("/{patientId}")
  public Mono<Response> getPatient(@PathVariable String patientId) {
    return patientService.findPatient(patientId);
  }

  @PutMapping("/{patientId}")
  public Mono<Response> updatePatient(
      @PathVariable("patientId") String patientId, @RequestBody PatientDto patientDto) {
    return patientService.updatePatient(patientId, patientDto);
  }

  @DeleteMapping("/{patientId}")
  public Mono<Response> deletePatient(@PathVariable("patientId") String patientId) {
    return patientService.deletePatient(patientId);
  }
}