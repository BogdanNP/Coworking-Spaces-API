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

import com.example.demo.handlers.RoomHandler;
import com.example.demo.models.DataResponse;
import com.example.demo.repositories.RoomRepository;

@Controller 
@RequestMapping(path="/demo") 
public class RoomController {
      
  @Autowired 
  private RoomRepository roomRepository;

  private RoomHandler roomHandler(){
    return RoomHandler.instance(this.roomRepository);
  }
    
  @PostMapping(path="/room/add") 
  public @ResponseBody DataResponse addNewRoom (@RequestBody String body) {
    return roomHandler().save(body);
  }

  @GetMapping(path="/room/all")
  public @ResponseBody DataResponse getAllRooms() {
   return roomHandler().findAll();
  }  

  @PutMapping(path="/room/update")
  public @ResponseBody DataResponse updateRoom (@RequestBody String body) {
    return roomHandler().update(body);
  }
  
  @DeleteMapping(path="/room/delete")
  public @ResponseBody DataResponse deleteRoom (@RequestParam("id") Integer id) {
      return roomHandler().delete(id);
  }
}
