package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    
}
