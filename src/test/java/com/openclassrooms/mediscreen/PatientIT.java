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
    mockMvc.perform(get("/patients/7"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.lastname", is("Simpson")));
  }

  @Test
  public void testGetPatientById_shouldReturnNotFound() throws Exception {
    mockMvc.perform(get("/patients/1"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testGetPatients_shouldReturnOk() throws Exception {
    mockMvc.perform(get("/patients"))
            .andExpect(status().isOk());
  }

  @Test
  public void testUpdatePatient_shouldReturnOk() throws Exception {
    mockMvc.perform(put("/patients/7")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"lastname\":\"Simpson\",\"firstname\":\"Homer\",\"birthdate\":\"1960-12-31\",\"sex\":\"M\",\"address\":\"SpringField\",\"phone\":\"555-555\"}"))
            .andExpect(status().isOk());
  }

  @Test
  public void testUpdatePatient_shouldReturnUnprocessableEntity() throws Exception {
    mockMvc.perform(put("/patients/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"lastname\":\"test\"}"))
            .andExpect(status().isUnprocessableEntity());
  }

  //TODO: delete patient by lastname and firstname after
  /*@Test
  public void testPostPatient_shouldReturnOk() throws Exception {
    mockMvc.perform(post("/patients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"lastname\":\"test\",\"firstname\":\"test\"}"))
            .andExpect(status().isOk());
  }*/

  @Test
  public void testPostPatient_shouldReturnUnprocessableEntity() throws Exception {
    mockMvc.perform(post("/patients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"lastname\":\"Simpson\",\"firstname\":\"Homer\"}"))
            .andExpect(status().isUnprocessableEntity());
  }

  //TODO: delete patient by lastname and firstname
  /*@Test
  public void testDeletePatient_shouldReturnOk() throws Exception {
    mockMvc.perform(delete("/patients/1"))
            .andExpect(status().isOk());
  }*/

  @Test
  public void testDeletePatient_shouldReturnNotFound() throws Exception {
    mockMvc.perform(delete("/patients/1"))
            .andExpect(status().isNotFound());
  }

}
