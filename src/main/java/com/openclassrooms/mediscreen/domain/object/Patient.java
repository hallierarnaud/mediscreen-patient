package com.openclassrooms.mediscreen.domain.object;

import java.util.Date;

import lombok.Data;

@Data
public class Patient {

  private Long id;
  private String lastname;
  private String firstname;
  private Date birthdate;
  private String sex;
  private String address;
  private String phone;

}
