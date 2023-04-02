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
  
  @PostMapping(path="/order/add") 
  public @ResponseBody DataResponse addNewOrder (@RequestBody String body) {
    return orderPHandler().save(body);
  }

  @GetMapping(path="/order/all")
  public @ResponseBody DataResponse getAllOrders() {
   return orderPHandler().findAll();
  }  

  @PutMapping(path="/order/update")
  public @ResponseBody DataResponse updateDeskRequest (@RequestBody String body){
    return orderPHandler().update(body);
  }
  
  @DeleteMapping(path="/order/delete")
  public @ResponseBody DataResponse deleteDeskRequest (@RequestParam("id") Integer id) {
    return orderPHandler().delete(id);
  }
}
