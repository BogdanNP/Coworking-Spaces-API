package com.example.demo.models;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class UserP extends DataModel{

  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  private String type;
  @Id
  private String username;
  private String password;

  public UserP(){}

  /**
  * Creates a UserP object from a JSON String.
  * @param json 
  * @throws JsonMappingException
  * @throws JsonProcessingException
  */
  public UserP(String json) throws JsonMappingException, JsonProcessingException {
  super(json);
      ObjectMapper mapper = new ObjectMapper();
      Map<String, Object> map = mapper.readValue(json, Map.class);
      setUsername((String)map.get("username"));
      setPassword((String)map.get("password"));
      setType((String)map.get("type"));
  }
  
  /**
  * Copies all the data from one source user to the current object.
  * @param source
  */
  @Override
  public void updateFrom(DataModel dataModel){
    UserP source = (UserP) dataModel;
      if(source.getUsername() != null){
        setUsername(source.getUsername());
      }
      if(source.getPassword() != null){
        setPassword(source.getPassword());
      }
      if(source.getType() != null){
        setType(source.getType());
      }
  }

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

  @Override 
  public boolean equals(Object obj) {
      if(obj.getClass() != UserP.class){
          return false;
      }
      UserP userP = (UserP)obj;
      return id == userP.getId() 
      && username == userP.getUsername()
      && password == userP.getPassword()
      && type.equals(userP.getType()); 
  }

  @Override
  public String toString() {
     return "UserP[id="+id
     + "; username="+username
     + "; type="+type
     + "; password="+password
     + "]";
  }

}