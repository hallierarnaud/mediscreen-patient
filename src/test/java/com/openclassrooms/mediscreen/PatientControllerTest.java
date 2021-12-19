package com.openclassrooms.mediscreen;

import com.openclassrooms.mediscreen.controller.endpoint.PatientController;
import com.openclassrooms.mediscreen.domain.object.Patient;
import com.openclassrooms.mediscreen.domain.service.PatientService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PatientController.class)
public class PatientControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private PatientService patientService;

  @Test
  public void getPatientById_shouldReturnOk() throws Exception {
    when(patientService.getPatient(any())).thenReturn(new Patient());
    mockMvc.perform(get("/patients/1")).andExpect(status().isOk());
  }

  @Test
  public void getPatientById_shouldReturnNotFound() throws Exception {
    when(patientService.getPatient(any())).thenThrow(NoSuchElementException.class);
    mockMvc.perform(get("/patients/1")).andExpect(status().isNotFound());
  }

  @Test
  public void getPatients_shouldReturnOk() throws Exception {
    when(patientService.getPatient(any())).thenReturn(new Patient());
    mockMvc.perform(get("/patients")).andExpect(status().isOk());
  }

  @Test
  public void updatePatient_shouldReturnOk() throws Exception {
    when(patientService.updatePatient(any(), any())).thenReturn(new Patient());
    mockMvc.perform(put("/patients/1")
                    .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isOk());
  }

  @Test
  public void updatePatient_shouldReturnUnprocessableEntity() throws Exception {
    when(patientService.updatePatient(any(), any())).thenThrow(NoSuchElementException.class);
    mockMvc.perform(put("/patients/1")
                    .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isUnprocessableEntity());
  }

  @Test
  public void postPatient_shouldReturnOk() throws Exception {
    when(patientService.addPatient(any())).thenReturn(new Patient());
    mockMvc.perform(post("/patients")
                    .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isOk());
  }

  @Test
  public void postPatient_shouldReturnUnprocessableEntity() throws Exception {
    when(patientService.addPatient(any())).thenThrow(EntityExistsException.class);
    mockMvc.perform(post("/patients")
                    .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isUnprocessableEntity());
  }

  @Test
  public void deletePatient_shouldReturnOk() throws Exception {
    doNothing().when(patientService).deletePatient(any());
    mockMvc.perform(delete("/patients/1")).andExpect(status().isOk());
  }

  @Test
  public void deletePatient_shouldReturnNotFound() throws Exception {
    doThrow(NoSuchElementException.class).when(patientService).deletePatient(any());
    mockMvc.perform(delete("/patients/1")).andExpect(status().isNotFound());
  }
  
}
