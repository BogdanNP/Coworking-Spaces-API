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

import com.example.demo.models.Order;

import com.example.demo.repositories.OrderRepository;

@Controller 
@RequestMapping(path="/demo") 
public class OrderController {
    
  @Autowired 
  private OrderRepository orderRepository;
    
  @PostMapping(path="/order/add") 
  public @ResponseBody String addNewOrder (
      @RequestParam Float total, 
      @RequestParam Integer userId, 
      @RequestParam Integer deskId, 
      @RequestParam String status
    ) {
    Order order = new Order();
    order.setTotal(total);
    order.setUserId(userId);
    order.setDeskId(deskId);
    order.setStatus(status);
    orderRepository.save(order);
    return "Order Saved";
  }

  @GetMapping(path="/order/all")
  public @ResponseBody Iterable<Order> getAllOrders() {
   return orderRepository.findAll();
  }  

  @PutMapping(path="/order/update")
  public @ResponseBody String updateDeskRequest (
      @RequestParam Integer id,
      @RequestParam(required = false)  Float total, 
      @RequestParam(required = false) Integer userId, 
      @RequestParam(required = false) Integer deskId, 
      @RequestParam(required = false) String status
    ) {
    Iterable<Order> orders = orderRepository.findAll();
    orders.forEach(order -> {
      if(order.getId().equals(id)){
        order.setTotal(total);
        order.setUserId(userId);
        order.setDeskId(deskId);
        order.setStatus(status);
        orderRepository.save(order);
      }
    });
    return "Order Updated!";
  }
  
  @DeleteMapping(path="/order/delete")
  public @ResponseBody String deleteDeskRequest (
      @RequestParam("id") Integer id
    ) {
    Iterable<Order> orders = orderRepository.findAll();
    orders.forEach(order -> {
      if(order.getId().equals(id)){
        orderRepository.delete(order);
      }
    });
    return "Order Deleted!";
  }
  
    
}
