package com.example.demo.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.models.DeskRequest;
import com.example.demo.repositories.DeskRequestRepository;

@Controller 
@RequestMapping(path="/demo") 
public class DeskRequestController {

  @Autowired 
  private DeskRequestRepository deskRequestRepository;  
    
  
  @PostMapping(path="/desk_request/add")
  public @ResponseBody String addNewDeskRequest (
      @RequestParam String status, 
      @RequestParam Integer userId, 
      @RequestParam Integer deskId, 
      @RequestParam Date startDate, 
      @RequestParam Date endDate 
    ) {
    DeskRequest deskRequest = new DeskRequest();
    deskRequest.setStatus(status);
    deskRequest.setUserId(userId);
    deskRequest.setDeskId(deskId);
    deskRequest.setStartDate(startDate);
    deskRequest.setEndDate(endDate);
    deskRequestRepository.save(deskRequest);
    return "Saved";
  }

  @GetMapping(path="/desk_request/all")
  public @ResponseBody Iterable<DeskRequest> getAllDeskRequests() {
   return deskRequestRepository.findAll();
  }


  
  @PutMapping(path="/desk_request/update")
  public @ResponseBody String updateDeskRequest (
      @RequestParam Integer id,
      @RequestParam(required = false) String status, 
      @RequestParam(required = false) Integer userId, 
      @RequestParam(required = false) Integer deskId, 
      @RequestParam(required = false) Date startDate,
      @RequestParam(required = false) Date endDate
    ) {
    Iterable<DeskRequest> deskRequests = deskRequestRepository.findAll();
    deskRequests.forEach(deskRequest -> {
      if(deskRequest.getId().equals(id)){
        deskRequestRepository.delete(deskRequest);
        deskRequest.setStatus(status);
        deskRequest.setUserId(userId);
        deskRequest.setDeskId(deskId);
        deskRequest.setStartDate(startDate);
        deskRequest.setEndDate(endDate);
        deskRequestRepository.save(deskRequest);
      }
    });
    return "Desk Request Updated!";
  }
  
  @DeleteMapping(path="/desk_request/delete")
  public @ResponseBody String deleteDeskRequest (
      @RequestParam("id") String id
    ) {
    Iterable<DeskRequest> deskRequests = deskRequestRepository.findAll();
    deskRequests.forEach(deskRequest -> {
      if(deskRequest.getId().equals(id)){
        deskRequestRepository.delete(deskRequest);
      }
    });
    return "Desk Request Deleted!";
  }
  
    
}
