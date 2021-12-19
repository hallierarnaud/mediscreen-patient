package com.openclassrooms.mediscreen;

import com.openclassrooms.mediscreen.domain.object.Patient;
import com.openclassrooms.mediscreen.model.DAO.PatientDAO;
import com.openclassrooms.mediscreen.model.entity.PatientEntity;
import com.openclassrooms.mediscreen.model.repository.PatientRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientDAOTest {
  
  @Mock
  private PatientRepository patientRepository;
  
  @InjectMocks
  private PatientDAO patientDAO;

  @Test
  public void findPatientById_shouldReturnOk() {
    // GIVEN
    PatientEntity patientEntity = new PatientEntity();
    Patient patient = new Patient();
    when(patientRepository.findById(anyLong())).thenReturn(java.util.Optional.of(patientEntity));

    // WHEN
    Patient actualPatient = patientDAO.findById(anyLong());

    // THEN
    assertEquals(patient, actualPatient);
    verify(patientRepository).findById(anyLong());
  }

  @Test
  public void findPatientById_shouldReturnNotFound() {
    // GIVEN
    Patient patient = new Patient();
    patient.setId(1L);

    // THEN
    assertThrows(NoSuchElementException.class, () -> patientDAO.findById(patient.getId()));
  }

  @Test
  public void findAllPatients_shouldReturnOk() {
    // GIVEN
    List<PatientEntity> patientEntities = new ArrayList<>();
    patientEntities.add(new PatientEntity());
    List<Patient> patients = new ArrayList<>();
    patients.add(new Patient());
    when(patientRepository.findAll()).thenReturn(patientEntities);

    // WHEN
    List<Patient> actualPatients = patientDAO.findAll();

    // THEN
    assertEquals(patients, actualPatients);
    verify(patientRepository).findAll();
  }

  @Test
  public void updatePatient_shouldReturnOK() {
    // GIVEN
    PatientEntity patientEntity = new PatientEntity();
    Patient patient = new Patient();
    patient.setId(1L);
    patient.setFirstname("Homer");
    when(patientRepository.findById(anyLong())).thenReturn(java.util.Optional.of(patientEntity));
    when(patientRepository.save(any(PatientEntity.class))).thenReturn(patientEntity);

    // WHEN
    Patient updatedPatient = patientDAO.updatePatient(1l, patient);

    // THEN
    assertEquals("Homer", updatedPatient.getFirstname());
    verify(patientRepository).findById(anyLong());
    verify(patientRepository).save(any(PatientEntity.class));
  }

  @Test
  public void updatePatient_shouldReturnNotFound() {
    // GIVEN
    Patient patient = new Patient();
    patient.setId(1L);

    // THEN
    Throwable exception = assertThrows(NoSuchElementException.class, () -> patientDAO.updatePatient(patient.getId(), patient));
    assertEquals("patient 1 doesn't exist", exception.getMessage());
  }

  @Test
  public void addPatient_shouldReturnOK() {
    // GIVEN
    PatientEntity patientEntity = new PatientEntity();
    Patient patient = new Patient();
    patient.setId(1L);
    patient.setFirstname("Homer");
    when(patientRepository.save(any(PatientEntity.class))).thenReturn(patientEntity);

    // WHEN
    Patient addedPatient = patientDAO.addPatient(patient);

    // THEN
    assertEquals("Homer", addedPatient.getFirstname());
    verify(patientRepository).save(any(PatientEntity.class));
  }

  @Test
  public void deletePatientById_shouldReturnOk() {
    // GIVEN
    PatientEntity patientEntity = new PatientEntity();
    patientEntity.setId(1L);

    // WHEN
    patientRepository.deleteById(patientEntity.getId());

    // THEN
    verify(patientRepository).deleteById(anyLong());
  }

  @Test
  public void patientExistById_shouldReturnOk() {
    // GIVEN
    when(patientRepository.existsById(anyLong())).thenReturn(true);

    // WHEN
    Boolean actual = patientDAO.existById(anyLong());

    // THEN
    assertEquals(true, actual);
    verify(patientRepository).existsById(anyLong());
  }
  
}
