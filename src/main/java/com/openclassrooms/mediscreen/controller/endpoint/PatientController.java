package com.openclassrooms.mediscreen.controller.endpoint;

import com.openclassrooms.mediscreen.controller.DTO.PatientRequest;
import com.openclassrooms.mediscreen.domain.object.Patient;
import com.openclassrooms.mediscreen.domain.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

@CrossOrigin(origins="*")
@RestController
public class PatientController {

  @Autowired
  private PatientService patientService;

  /**
   * @param id a patient's id
   * @return a patient corresponding to the id
   */
  @GetMapping("/patients/{id}")
  public Patient getPatientById(@PathVariable("id") long id) {
    try {
      return patientService.getPatient(id);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "patient " + id + " doesn't exist");
    }
  }

  /**
   * @return a list of all patients in the database
   */
  @GetMapping("/patients")
  public List<Patient> getPatients() {
    return patientService.getPatients();
  }

  /**
   * @param id a patient's id
   * @param patientRequest a patient defined by his attributes
   * @return update the patient in the database
   */
  @PutMapping("/patients/{id}")
  public Patient updatePatient(@PathVariable("id") long id, @RequestBody PatientRequest patientRequest) {
    try {
      return patientService.updatePatient(id, patientRequest);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "patient " + id + " doesn't exist");
    }
  }

  /**
   * @param patientRequest a patient defined by his attributes
   * @return add the patient to the database
   */
  @PostMapping("/patients")
  public Patient addPatient(@RequestBody PatientRequest patientRequest) {
    try {
      return patientService.addPatient(patientRequest);
    } catch (EntityExistsException e) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "patient " + patientRequest.getLastname() + " already exists");
    }
  }

  /**
   * @param id a patient's id
   * delete the patient in the database
   */
  @DeleteMapping("/patients/{id}")
  public void deletePatientById(@PathVariable("id") long id) {
    try {
      patientService.deletePatient(id);
      throw new ResponseStatusException(HttpStatus.OK, "patient " + id + " was deleted");
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "patient " + id + " doesn't exist");
    }
  }

}
