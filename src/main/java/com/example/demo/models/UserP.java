package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class UserP {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  private String type;
  private String username;
  private String password;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  } 
  
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}