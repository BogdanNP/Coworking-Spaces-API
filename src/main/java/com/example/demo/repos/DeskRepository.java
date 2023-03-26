package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Desk;

public interface DeskRepository extends CrudRepository<Desk, Integer> {
    
}
