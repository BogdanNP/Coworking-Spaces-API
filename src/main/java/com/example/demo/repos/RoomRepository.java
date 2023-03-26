package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Room;

public interface RoomRepository extends CrudRepository<Room, Integer> {
    
}
