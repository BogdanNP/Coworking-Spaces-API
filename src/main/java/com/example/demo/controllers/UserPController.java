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
    
    
    /**
     * @return UserPHandler -> the object which handles the logic between the api and the database.
     */
    private UserPHandler userPHandler(){
      return UserPHandler.instance(this.userPRepository);
    }
  
  /**
  * Creates and new user and saves it in the DB.
  * @param body String -> desk object in json format
  * @apiNote user object contains the following:
  * @apiNote username
  * @apiNote password
  * @apiNote type -> the type of the user ("ADMIN", "CLIENT")
  * @return DataResponse (status, message).
  */
  @PostMapping(path="/user/add")
  public @ResponseBody DataResponse addNewUser (@RequestBody String body) {
    return userPHandler().save(body);
  }

  /**
  * Returns all the users from the DB.
  * @return DataResponse (status, message, list of users)
  */
  @GetMapping(path="/user/all")
  public @ResponseBody DataResponse getAllPersons() {
   return userPHandler().findAll();
  } 

  /**
  * Updates an existing user from DB.
  * @param body String -> desk object in json format
  * @apiNote user object contains the following:
  * @apiNote username
  * @apiNote password
  * @apiNote type -> the type of the user ("ADMIN", "CLIENT")
  * @return DataResponse (status, message).
  */
  @PutMapping(path="/user/update")
  public @ResponseBody DataResponse updateUser (@RequestBody String body) {
      return userPHandler().update(body);
  }

  /**
  * Deletes a user from DB.
  * @param id String -> desk id 
  * @return DataResponse (status, message).
  */
  @DeleteMapping(path="/user/delete")
  public @ResponseBody DataResponse deleteUsername (@RequestParam("id") Integer id) {
      return userPHandler().delete(id);
  }
  
  
}