package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.DeskAlignment;

public interface DeskAlignmentRepository extends CrudRepository<DeskAlignment, Integer>{
    
}
