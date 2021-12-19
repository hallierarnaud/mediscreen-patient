package com.openclassrooms.mediscreen;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientIT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testGetPatientById_shouldReturnOk() throws Exception {
    mockMvc.perform(get("/patients/2"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.lastname", is("Kent")));
  }

  @Test
  public void testGetPatientById_shouldReturnNotFound() throws Exception {
    mockMvc.perform(get("/patients/1000"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testGetPatients_shouldReturnOk() throws Exception {
    mockMvc.perform(get("/patients"))
            .andExpect(status().isOk());
  }

  @Test
  public void testUpdatePatient_shouldReturnOk() throws Exception {
    mockMvc.perform(put("/patients/2")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"lastname\":\"SuperMan\", \"birthdate\":\"1900-01-01\"}"))
            .andExpect(status().isOk());
  }

  @Test
  public void testUpdatePatient_shouldReturnUnprocessableEntity() throws Exception {
    mockMvc.perform(put("/patients/1000")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"lastname\":\"SuperMan\", \"birthdate\":\"1900-01-01\"}"))
            .andExpect(status().isUnprocessableEntity());
  }

  @Test
  public void testPostPatient_shouldReturnOk() throws Exception {
    mockMvc.perform(post("/patients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"id\":\"1\",\"lastname\":\"Kent\",\"firstname\":\"Clark\",\"birthdate\":\"1900-01-01\"}"))
            .andExpect(status().isOk());
  }

  /*
  @Test
  public void testPostPatient_shouldReturnUnprocessableEntity() throws Exception {
    mockMvc.perform(post("/patients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"id\":\"1\",\"lastname\":\"Kent\",\"firstname\":\"Clark\",\"birthdate\":\"1900-01-01\"}"))
            .andExpect(status().isUnprocessableEntity());
  }*/

  /*
  @Test
  public void testDeletePatient_shouldReturnOk() throws Exception {
    mockMvc.perform(delete("/patients/4"))
            .andExpect(status().isOk());
  }*/

  @Test
  public void testDeletePatient_shouldReturnNotFound() throws Exception {
    mockMvc.perform(delete("/patients/4"))
            .andExpect(status().isNotFound());
  }

}
