package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Desk;

public interface DeskRepository extends CrudRepository<Desk, Integer> {
    
}
