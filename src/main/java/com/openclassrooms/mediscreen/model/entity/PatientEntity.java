package com.openclassrooms.mediscreen.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "patient")
public class PatientEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Lastname is mandatory")
  @Column(name = "lastname", length = 45)
  private String lastname;

  @NotBlank(message = "Firstname is mandatory")
  @Column(name = "firstname", length = 45)
  private String firstname;

  @Column(name = "birthdate")
  private Date birthdate;

  @Column(name = "sex", length = 1)
  private String sex;

  @Column(name = "address", length = 200)
  private String address;

  @Column(name = "phone", length = 15)
  private String phone;

}
