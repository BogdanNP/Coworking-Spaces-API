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

  /**
   * Creates a new desk request and saves it in the DB.
   * @param body String -> desk request object in json format
   * @apiNote desk request object contains the following:
   * @apiNote startDate -> the end date of the desk request
   * @apiNote endDate -> the end date of the desk request
   * @apiNote userId -> the userId 
   * @apiNote deskId -> the deskId of the desk request
   * @apiNote status -> the status of the desk (RESERED, CANCELLED)
   */
  @PostMapping(path="/desk_request/add")
  public @ResponseBody DataResponse addNewDeskRequest (@RequestBody String body) {
    return deskRequestHandler().save(body);
  }

  /**
  * Returns all the desk requests from the DB.
  * @return DataResponse (status, message, list of orders)
  */
  @GetMapping(path="/desk_request/all")
  public @ResponseBody DataResponse getAllDeskRequests() {
   return deskRequestHandler().findAll();
  }

   /**
  * Updates an existing desk request from DB.
  * @param body String -> desk request object in json format
  * @apiNote desk request object contains the following:
  * @apiNote id (REQUIRED) -> the id of the desk request
  * @apiNote startDate -> the end date of the desk request
  * @apiNote endDate -> the end date of the desk request
  * @apiNote userId -> the userId 
  * @apiNote deskId -> the deskId of the desk request
  * @apiNote status -> the status of the desk (RESERED, CANCELLED)
  * @return DataResponse (status, message).
  */
  @PutMapping(path="/desk_request/update")
  public @ResponseBody DataResponse updateDeskRequest (@RequestBody String body) {
    return deskRequestHandler().update(body);
  }

  /**
  * Deletes a desk request from DB.
  * @param id String -> order id 
  * @return DataResponse (status, message).
  */
  @DeleteMapping(path="/desk_request/delete")
  public @ResponseBody DataResponse deleteDeskRequest (@RequestParam("id") Integer id) {
    return deskRequestHandler().delete(id);
  }
}
