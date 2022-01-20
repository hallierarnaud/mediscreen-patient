package com.openclassrooms.mediscreen.domain.service;

import com.openclassrooms.mediscreen.controller.DTO.PatientRequest;
import com.openclassrooms.mediscreen.domain.object.Patient;
import com.openclassrooms.mediscreen.model.DAO.PatientDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.EntityExistsException;

import lombok.Data;

@Data
@Service
public class PatientService {

  @Autowired
  private PatientDAO patientDAO;

  public Patient getPatient(final Long id) {
    if (patientDAO.findById(id) == null) {
      throw new NoSuchElementException("Patient " + id + " doesn't exist");
    }
    return patientDAO.findById(id);
  }

  public List<Patient> getPatients() {
    return StreamSupport.stream(patientDAO.findAll().spliterator(),false)
            .collect(Collectors.toList());
  }

  public Patient updatePatient(final Long id, PatientRequest patientRequest) {
    if (patientDAO.findById(id) == null) {
      throw new NoSuchElementException("Patient " + id + " doesn't exist");
    }
    Patient patient = patientDAO.findById(id);
    patient.setId(id);
    updatePatientWithPatientRequest(patient, patientRequest);
    return patientDAO.updatePatient(id, patient);
  }

  public Patient addPatient(PatientRequest patientRequest) {
    if (patientDAO.existByLastnameAndFirstName(patientRequest.getLastname(), patientRequest.getFirstname())) {
      throw new EntityExistsException("Patient " + patientRequest.getLastname() + " " + patientRequest.getFirstname() + " already exists");
    }
    Patient patient = new Patient();
    updatePatientWithPatientRequest(patient, patientRequest);
    return patientDAO.addPatient(patient);
  }

  public void deletePatient(final Long id) {
    if (!patientDAO.existById(id)) {
      throw new NoSuchElementException("Patient " + id + " doesn't exist");
    }
    patientDAO.deletePatientById(id);
  }

  public Patient updatePatientWithPatientRequest(Patient patient, PatientRequest patientRequest) {
    patient.setLastname(patientRequest.getLastname());
    patient.setFirstname(patientRequest.getFirstname());
    patient.setBirthdate(patientRequest.getBirthdate());
    patient.setSex(patientRequest.getSex());
    patient.setAddress(patientRequest.getAddress());
    patient.setPhone(patientRequest.getPhone());
    return patient;
  }

}
