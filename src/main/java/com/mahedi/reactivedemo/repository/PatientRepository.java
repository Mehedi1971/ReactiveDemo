package com.mahedi.reactivedemo.repository;

import com.mahedi.reactivedemo.model.Patient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PatientRepository extends ReactiveCrudRepository<Patient, Long> {

  Flux<Patient> findAllBy(Pageable pageable);

  Mono<Patient> findByPatientId(String patientId);

  Mono<Void> deleteByPatientId(String patientId);
}
