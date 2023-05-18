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

import com.example.demo.handlers.DeskHandler;
import com.example.demo.models.DataResponse;
import com.example.demo.repositories.DeskRepository;

@Controller 
@RequestMapping(path="/demo") 
public class DeskController {

  @Autowired 
  private DeskRepository deskRepository; 

  /**
   * @return DeskHandler -> the object which handles the logic between the api and the database.
   */
  private DeskHandler deskHandler(){
    return DeskHandler.instance(this.deskRepository);
  }

  /**
  * Creates a new desk and saves it in the DB.
  * @param body String -> desk object in json format
  * @apiNote desk object contains the following:
  * @apiNote width -> the width of the desk
  * @apiNote height -> the height of the desk
  * @apiNote length -> the length of the desk
  * @apiNote tariff -> the tariff of the desk for renting (price)
  * @apiNote tariffType -> the tariffType to set (ex. PRICE_PER_DAY, PRICE_PER_HOUR)
  * @return DataResponse (status, message).
  */
  @PostMapping(path="/desk/add") 
  public @ResponseBody DataResponse addNewDesk (@RequestBody String body) {
    return deskHandler().save(body);
  }
  
  /**
  * Returns all the desks from the DB.
  * @return DataResponse (status, message, list of desks)
  */
  @GetMapping(path="/desk/all")
  public @ResponseBody DataResponse getAllDesks() {
   return deskHandler().findAll();
  }  

  /**
  * Updates an existing desk from DB.
  * @param body String -> desk object in json format
  * @apiNote desk object contains the following:
  * @apiNote id (REQUIRED) -> the id of the desk
  * @apiNote width -> the width of the desk
  * @apiNote height -> the height of the desk
  * @apiNote length -> the length of the desk
  * @apiNote tariff -> the tariff of the desk for renting (price)
  * @apiNote tariffType -> the tariffType to set (ex. PRICE_PER_DAY, PRICE_PER_HOUR)
  * @return DataResponse (status, message).
  */
  @PutMapping(path="/desk/update")
  public @ResponseBody DataResponse updateDesk (@RequestBody String body) {
    return deskHandler().update(body);
  }

  /**
  * Deletes a desk from DB.
  * @param id String -> desk id 
  * @return DataResponse (status, message).
  */
  @DeleteMapping(path="/desk/delete")
  public @ResponseBody DataResponse deleteDesk (@RequestParam("id") Integer id) {
      return deskHandler().delete(id);
  }

  @GetMapping(path="/desk/room")
  public @ResponseBody DataResponse getDesksByRoomId(@RequestParam("id") Integer id) {
   return deskHandler().findByRoomId(id);
  }  
  
}
