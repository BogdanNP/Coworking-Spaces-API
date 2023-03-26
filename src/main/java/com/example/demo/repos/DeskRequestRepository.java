package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.DeskRequest;


public interface DeskRequestRepository extends CrudRepository<DeskRequest, Integer>{
    
}
