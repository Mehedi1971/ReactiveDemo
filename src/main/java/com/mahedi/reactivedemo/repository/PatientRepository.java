package com.mahedi.reactivedemo.repository;

import com.mahedi.reactivedemo.model.Patient;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends ReactiveCrudRepository<Patient, Long> {

}
