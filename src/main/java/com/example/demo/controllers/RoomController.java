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
  
   /**
  * Creates and new desk and saves it in the DB.
  * @param body String -> room object in json format
  * @apiNote room object contains the following:
  * @apiNote id (REQUIRED) -> the id of the room
  * @apiNote width -> the width of the room
  * @apiNote length -> the length of the room
  * @apiNote tariff -> the tariff of the desk for renting (price)
  * @apiNote deskList -> the deskList
  * @apiNote details -> the details of the room
  * @return DataResponse (status, message).
  */
  @PostMapping(path="/room/add") 
  public @ResponseBody DataResponse addNewRoom (@RequestBody String body) {
    return roomHandler().save(body);
  }

  /**
  * Returns all the rooms from the DB.
  * @return DataResponse (status, message, list of rooms)
  */
  @GetMapping(path="/room/all")
  public @ResponseBody DataResponse getAllRooms() {
   return roomHandler().findAll();
  }
  
    /**
  * Updates an existing room from DB.
  * @param body String -> room object in json format
  * @apiNote room object contains the following:
  * @apiNote id (REQUIRED) -> the id of the room
  * @apiNote width -> the width of the room
  * @apiNote length -> the length of the room
  * @apiNote tariff -> the tariff of the desk for renting (price)
  * @apiNote deskList -> the deskList
  * @apiNote details -> the details of the room
  * @return DataResponse (status, message).
  */
  @PutMapping(path="/room/update")
  public @ResponseBody DataResponse updateRoom (@RequestBody String body) {
    return roomHandler().update(body);
  }
  
  /**
  * Deletes a room from DB.
  * @param id String -> room id 
  * @return DataResponse (status, message).
  */
  @DeleteMapping(path="/room/delete")
  public @ResponseBody DataResponse deleteRoom (@RequestParam("id") Integer id) {
      return roomHandler().delete(id);
  }
}
