package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.models.Room;
import com.example.demo.repositories.RoomRepository;

@Controller 
@RequestMapping(path="/demo") 
public class RoomController {
      
  @Autowired 
  private RoomRepository roomRepository;

    
  @PostMapping(path="/room/add") 
  public @ResponseBody String addNewRoom (
      @RequestParam Integer width, 
      @RequestParam Integer length, 
      @RequestParam List<Integer> deskList, 
      @RequestParam String details
    ) {
    Room room = new Room();
    room.setWidth(width);
    room.setLength(length);
    room.setDeskList(deskList);
    room.setDetails(details);
    roomRepository.save(room);
    return "Saved";
  }

  @GetMapping(path="/room/all")
  public @ResponseBody Iterable<Room> getAllRooms() {
   return roomRepository.findAll();
  }  

  @PutMapping(path="/room/update")
  public @ResponseBody String updateRoom (
      @RequestParam Integer id,
      @RequestParam(required = false) Integer width, 
      @RequestParam(required = false) Integer length, 
      @RequestParam(required = false) List<Integer> deskList, 
      @RequestParam(required = false) String details
    ) {
    Iterable<Room> rooms = roomRepository.findAll();
    rooms.forEach(room -> {
      if(room.getId().equals(id)){
        roomRepository.delete(room);
        room.setWidth(width);
        room.setLength(length);
        room.setDeskList(deskList);
        room.setDetails(details);
        roomRepository.save(room);
      }
    });
    return "Room Updated!";
  }
  
  @DeleteMapping(path="/room/delete")
  public @ResponseBody String deleteRoom (
      @RequestParam("id") String id
    ) {
    Iterable<Room> rooms = roomRepository.findAll();
    rooms.forEach(room -> {
      if(room.getId().equals(id)){
        roomRepository.delete(room);
      }
    });
    return "Room Deleted!";
  }
}
