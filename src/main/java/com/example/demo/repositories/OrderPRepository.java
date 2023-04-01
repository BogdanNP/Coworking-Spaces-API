package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.OrderP;

public interface OrderPRepository extends CrudRepository<OrderP, Integer> {
    
}
