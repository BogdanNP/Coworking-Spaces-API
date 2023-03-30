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


import com.example.demo.models.Desk;
import com.example.demo.repositories.DeskRepository;

@Controller 
@RequestMapping(path="/demo") 
public class DeskController {

    @Autowired 
    private DeskRepository deskRepository; 

  @PostMapping(path="/desk/add") 
  public @ResponseBody String addNewDesk (
      @RequestParam Integer width, 
      @RequestParam Integer height, 
      @RequestParam Integer length, 
      @RequestParam Float tariff, 
      @RequestParam String tariffType
    ) {
    Desk desk = new Desk();
    desk.setWidth(width);
    desk.setHeight(height);
    desk.setLength(length);
    desk.setTariff(tariff);
    desk.setTariffType(tariffType);
    deskRepository.save(desk);
    return "Saved";
  }

  @GetMapping(path="/desk/all")
  public @ResponseBody Iterable<Desk> getAllDesks() {
   return deskRepository.findAll();
  }  

  @PutMapping(path="/desk/update")
  public @ResponseBody String updateDesk (
    @RequestParam(required = true) Integer id, 
    @RequestParam(required = false) Integer width, 
    @RequestParam(required = false) Integer height, 
    @RequestParam(required = false) Integer length, 
    @RequestParam(required = false) Float tariff, 
    @RequestParam(required = false) String tariffType
    ) {
    Iterable<Desk> desks = deskRepository.findAll();
    desks.forEach(desk -> {
      if(desk.getId().equals(id)){
        deskRepository.delete(desk);
        desk.setWidth(width);
        desk.setHeight(height);
        desk.setLength(length);
        desk.setTariff(tariff);
        desk.setTariffType(tariffType);
        deskRepository.save(desk);
      }
    });
    return "Desk Updated!";
  }
  
  @DeleteMapping(path="/desk/delete")
  public @ResponseBody String deleteDesk (
      @RequestParam("id") Integer id
    ) {
    Iterable<Desk> desks = deskRepository.findAll();
    desks.forEach(desk-> {
      if(desk.getId().equals(id)){
        deskRepository.delete(desk);
      }
    });
    return "Desk Deleted!";
  }

}
