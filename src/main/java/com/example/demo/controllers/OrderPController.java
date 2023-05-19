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

import com.example.demo.handlers.OrderPHandler;
import com.example.demo.models.DataResponse;

import com.example.demo.repositories.OrderPRepository;

@Controller 
@RequestMapping(path="/demo") 
public class OrderPController {
    
  @Autowired 
  private OrderPRepository orderPRepository;
  
  private OrderPHandler orderPHandler(){
    return OrderPHandler.instance(orderPRepository);
  }
  
  /**
   * Creates a new order and saves it in the DB.
   * @param body String -> order object in json format
   * @apiNote order object contains the following:
   * @apiNote id (REQUIRED) -> the id of the order
   * @apiNote total -> the total price of the order
   * @apiNote userId -> the userId 
   * @apiNote deskId -> the deskId of the order
   * @apiNote status -> the status of the order (PAID, PENDING, ...)
   * @return DataResponse (status, message).
   */
  @PostMapping(path="/order/add") 
  public @ResponseBody DataResponse addNewOrder (@RequestBody String body) {
    return orderPHandler().save(body);
  }

  /**
  * Returns all the orders from the DB.
  * @return DataResponse (status, message, list of orders)
  */
  @GetMapping(path="/order/all")
  public @ResponseBody DataResponse getAllOrders() {
   return orderPHandler().findAll();
  }

  
  /**
  * Updates an existing order from DB.
  * @param body String -> order object in json format
  * @apiNote order object contains the following:
  * @apiNote id (REQUIRED) -> the id of the order
  * @apiNote total -> the total price of the order
  * @apiNote userId -> the userId 
  * @apiNote deskId -> the deskId of the order
  * @apiNote status -> the status of the order (PAID, PENDING, ...)
  * @return DataResponse (status, message).
  */
  @PutMapping(path="/order/update")
  public @ResponseBody DataResponse updateDeskRequest (@RequestBody String body){
    return orderPHandler().update(body);
  }
  
  /**
  * Deletes an order from DB.
  * @param id String -> order id 
  * @return DataResponse (status, message).
  */
  @DeleteMapping(path="/order/delete")
  public @ResponseBody DataResponse deleteDeskRequest (@RequestParam("id") Integer id) {
    return orderPHandler().delete(id);
  }

  @GetMapping(path="/order/user")
  public @ResponseBody DataResponse getOrdersByUserId(@RequestParam("id") Integer id) {
   return orderPHandler().findByUserId(id);
  }
}
