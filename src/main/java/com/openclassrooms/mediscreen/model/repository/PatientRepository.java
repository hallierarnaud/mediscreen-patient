package com.openclassrooms.mediscreen.model.repository;

import com.openclassrooms.mediscreen.model.entity.PatientEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<PatientEntity, Long> {

  Boolean existsByLastname(String lastname);

}
