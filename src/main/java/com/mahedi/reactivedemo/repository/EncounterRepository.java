package com.mahedi.reactivedemo.repository;

import com.mahedi.reactivedemo.model.Encounter;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncounterRepository extends ReactiveCrudRepository<Encounter, Long> {

}
