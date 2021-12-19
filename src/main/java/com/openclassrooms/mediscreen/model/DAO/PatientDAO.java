package com.openclassrooms.mediscreen.model.DAO;

import com.openclassrooms.mediscreen.domain.object.Patient;
import com.openclassrooms.mediscreen.model.entity.PatientEntity;
import com.openclassrooms.mediscreen.model.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class PatientDAO {

  @Autowired
  private PatientRepository patientRepository;

  public Patient findById(Long id) {
    PatientEntity patientEntity = patientRepository.findById(id).orElseThrow(() -> new NoSuchElementException("pattient " + id + "doesn't exist"));
    Patient patient = new Patient();
    updatePatientWithPatientEntity(patient, patientEntity);
    return patient;
  }

  public List<Patient> findAll() {
    List<PatientEntity> patientEntities = StreamSupport.stream(patientRepository.findAll().spliterator(),false)
            .collect(Collectors.toList());
    return patientEntities.stream().map((patientEntity) -> {
      Patient patient = new Patient();
      updatePatientWithPatientEntity(patient, patientEntity);
      return patient;
    }).collect(Collectors.toList());
  }

  public Patient updatePatient(Long id, Patient patient) {
    PatientEntity patientEntity = patientRepository.findById(id).orElseThrow(() -> new NoSuchElementException("patient " + id + " doesn't exist"));
    updatePatientEntityWithPatient(patientEntity, patient);
    patientRepository.save(patientEntity);
    return patient;
  }

  public Patient addPatient(Patient patient) {
    PatientEntity patientEntity = new PatientEntity();
    updatePatientEntityWithPatient(patientEntity, patient);
    patientRepository.save(patientEntity);
    return patient;
  }

  public void deletePatientById(Long id) {
    patientRepository.deleteById(id);
  }

  public boolean existById(Long id) {
    return patientRepository.existsById(id);
  }

  public Patient updatePatientWithPatientEntity (Patient patient, PatientEntity patientEntity) {
    patient.setId(patientEntity.getId());
    patient.setLastname(patientEntity.getLastname());
    patient.setFirstname(patientEntity.getFirstname());
    patient.setBirthdate(patientEntity.getBirthdate());
    patient.setSex(patientEntity.getSex());
    patient.setAddress(patientEntity.getAddress());
    patient.setPhone(patientEntity.getPhone());
    return patient;
  }

  public PatientEntity updatePatientEntityWithPatient (PatientEntity patientEntity, Patient patient) {
    patientEntity.setId(patient.getId());
    patientEntity.setLastname(patient.getLastname());
    patientEntity.setFirstname(patient.getFirstname());
    patientEntity.setBirthdate(patient.getBirthdate());
    patientEntity.setSex(patient.getSex());
    patientEntity.setAddress(patient.getAddress());
    patientEntity.setPhone(patient.getPhone());
    return patientEntity;
  }

}
