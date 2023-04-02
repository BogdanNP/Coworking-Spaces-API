package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.repositories.UserPRepository;
import com.example.demo.handlers.UserPHandler;
import com.example.demo.models.DataResponse;

@Controller 
@RequestMapping(path="/demo") 
public class UserPController{
    @Autowired
    private UserPRepository userPRepository;
    
    private UserPHandler userPHandler(){
      return UserPHandler.instance(this.userPRepository);
    }

  @PostMapping(path="/user/add")
  public @ResponseBody DataResponse addNewUser (@RequestBody String body) {
    return userPHandler().save(body);
  }

  @GetMapping(path="/user/all")
  public @ResponseBody DataResponse getAllPersons() {
   return userPHandler().findAll();
  } 

  @PutMapping(path="/user/update")
  public @ResponseBody DataResponse updateUser (@RequestBody String body) {
      return userPHandler().update(body);
  }

  @DeleteMapping(path="/user/delete")
  public @ResponseBody DataResponse deleteUsername (@RequestParam("id") Integer id) {
      return userPHandler().delete(id);
  }
  
  
}