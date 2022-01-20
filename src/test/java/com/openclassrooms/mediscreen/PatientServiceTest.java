package com.openclassrooms.mediscreen;

import com.openclassrooms.mediscreen.controller.DTO.PatientRequest;
import com.openclassrooms.mediscreen.domain.object.Patient;
import com.openclassrooms.mediscreen.domain.service.PatientService;
import com.openclassrooms.mediscreen.model.DAO.PatientDAO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {
  
  @Mock
  private PatientDAO patientDAO;
  
  @InjectMocks
  private PatientService patientService;

  @Test
  public void getPatient_shouldReturnOk () {
    // GIVEN
    Patient patient = new Patient();
    patient.setId(1L);
    patient.setFirstname("Homer");
    when(patientDAO.findById(anyLong())).thenReturn(patient);

    // WHEN
    Patient actualPatient = patientService.getPatient(patient.getId());

    // THEN
    assertEquals("Homer", actualPatient.getFirstname());
    verify(patientDAO, times(2)).findById(patient.getId());
  }

  @Test
  public void getPatient_shouldReturnNotFound () {
    // GIVEN
    Patient patient = new Patient();
    patient.setId(1L);

    // WHEN
    when(patientDAO.findById(anyLong())).thenReturn(null);

    // THEN
    assertThrows(NoSuchElementException.class, () -> patientService.getPatient(patient.getId()));
    verify(patientDAO).findById(anyLong());
  }

  @Test
  public void getPatients_shouldReturnOk () {
    // GIVEN
    List<Patient> patients = new ArrayList<>();
    patients.add(new Patient());
    when(patientDAO.findAll()).thenReturn(patients);

    // WHEN
    List<Patient> actualPatients = patientService.getPatients();

    // THEN
    assertEquals(patients, actualPatients);
    verify(patientDAO).findAll();
  }

  @Test
  public void updatePatient_shouldReturnOk () {
    // GIVEN
    PatientRequest patientRequest = new PatientRequest();
    patientRequest.setFirstname("Homer");
    Patient patient = new Patient();
    patient.setId(1L);
    when(patientDAO.findById(anyLong())).thenReturn(patient);
    when(patientDAO.updatePatient(anyLong(), any(Patient.class))).thenReturn(patient);

    // WHEN
    Patient updatedPatient = patientService.updatePatient(patient.getId(), patientRequest);

    // THEN
    assertEquals(patient.getFirstname(), updatedPatient.getFirstname());
    verify(patientDAO, times(2)).findById(anyLong());
    verify(patientDAO).updatePatient(patient.getId(), patient);
  }

  @Test
  public void updatePatient_shouldReturnNotFound () {
    // GIVEN
    Patient patient = new Patient();
    patient.setId(1L);
    PatientRequest patientRequest = new PatientRequest();

    // THEN
    Throwable exception = assertThrows(NoSuchElementException.class, () -> patientService.updatePatient(patient.getId(), patientRequest));
    assertEquals("Patient 1 doesn't exist", exception.getMessage());
  }

  @Test
  public void addPatient_shouldReturnOk () {
    // GIVEN
    PatientRequest patientRequest = new PatientRequest();
    patientRequest.setFirstname("Homer");
    Patient patient = new Patient();
    when(patientDAO.addPatient(any(Patient.class))).thenReturn(patient);

    // WHEN
    Patient addedPatient = patientService.addPatient(patientRequest);

    // THEN
    assertEquals(patient.getFirstname(), addedPatient.getFirstname());
    verify(patientDAO).addPatient(any(Patient.class));
  }

  @Test
  public void addPatient_shouldReturnAlreadyExist () {
    // GIVEN
    PatientRequest patientRequest = new PatientRequest();
    patientRequest.setLastname("Simpson");
    patientRequest.setFirstname("Homer");

    // WHEN
    when(patientDAO.existByLastnameAndFirstName(anyString(), anyString())).thenReturn(TRUE);

    // THEN
    Throwable exception = assertThrows(EntityExistsException.class, () -> patientService.addPatient(patientRequest));
    assertEquals("Patient Simpson Homer already exists", exception.getMessage());
  }

  @Test
  public void deletePatient_shouldReturnOk () {
    // GIVEN
    Patient patient = new Patient();
    patient.setId(1L);
    when(patientDAO.existById(anyLong())).thenReturn(TRUE);

    // WHEN
    patientService.deletePatient(patient.getId());

    // THEN
    verify(patientDAO).deletePatientById(patient.getId());
  }

  @Test
  public void deletePatient_shouldReturnNotFound () {
    // GIVEN
    Patient patient = new Patient();
    patient.setId(1L);

    // WHEN
    when(patientDAO.existById(anyLong())).thenReturn(FALSE);

    // THEN
    Throwable exception = assertThrows(NoSuchElementException.class, () -> patientService.deletePatient(patient.getId()));
    assertEquals("Patient 1 doesn't exist", exception.getMessage());
  }

}
