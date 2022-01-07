package com.openclassrooms.mediscreen.controller.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class PatientRequest {

  private String lastname;
  private String firstname;
  private Date birthdate;
  private String sex;
  private String address;
  private String phone;

}
