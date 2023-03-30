package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.repositories.UserPRepository;
import com.example.demo.models.UserP;

@Controller 
@RequestMapping(path="/demo") 
public class UserPController{
    @Autowired
    private UserPRepository userPRepository;
    
  @PostMapping(path="/user/add")
  public @ResponseBody String addNewUser (
      @RequestParam String username
    ) {

    UserP user = new UserP();
    user.setUsername(username);
    userPRepository.save(user);
    return "User Saved";
  }

  @GetMapping(path="/user/all")
  public @ResponseBody Iterable<UserP> getAllPersons() {
   return userPRepository.findAll();
  } 

  @PutMapping(path="/user/update")
  public @ResponseBody String updateUser (
      @RequestParam String username,
      @RequestParam String password
    ) {
    Iterable<UserP> users = userPRepository.findAll();
    users.forEach(user -> {
      if(user.getUsername().equals(username)){
        userPRepository.delete(user);
        user.setPassword(password);
        userPRepository.save(user);
      }
    });
    return "User Updated!";
  }
  @DeleteMapping(path="/user/delete")
  public @ResponseBody String deleteUsername (
      @RequestParam("username") String username
    ) {
    Iterable<UserP> users = userPRepository.findAll();
    users.forEach(user -> {
      if(user.getUsername().equals(username)){
        userPRepository.delete(user);
      }
    });
    return "User Deleted!";
  }
  
  
}