package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.UserP;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserPRepository extends CrudRepository<UserP, Integer> {

}