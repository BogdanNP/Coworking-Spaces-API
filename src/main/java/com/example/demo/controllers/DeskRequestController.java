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

import com.example.demo.handlers.DeskRequestHandler;
import com.example.demo.models.DataResponse;
import com.example.demo.repositories.DeskRequestRepository;

@Controller 
@RequestMapping(path="/demo") 
public class DeskRequestController {

  @Autowired 
  private DeskRequestRepository deskRequestRepository;  
  
  private DeskRequestHandler deskRequestHandler(){
    return DeskRequestHandler.instance(deskRequestRepository);
  }
  
  @PostMapping(path="/desk_request/add")
  public @ResponseBody DataResponse addNewDeskRequest (@RequestBody String body) {
    return deskRequestHandler().save(body);
  }

  @GetMapping(path="/desk_request/all")
  public @ResponseBody DataResponse getAllDeskRequests() {
   return deskRequestHandler().findAll();
  }
  
  @PutMapping(path="/desk_request/update")
  public @ResponseBody DataResponse updateDeskRequest (@RequestBody String body) {
    return deskRequestHandler().update(body);
  }
  
  @DeleteMapping(path="/desk_request/delete")
  public @ResponseBody DataResponse deleteDeskRequest (@RequestParam("id") Integer id) {
    return deskRequestHandler().delete(id);
  }
}
