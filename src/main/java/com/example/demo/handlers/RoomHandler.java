package com.example.demo.handlers;

import com.example.demo.models.DataResponse;
import com.example.demo.models.Room;
import com.example.demo.repositories.RoomRepository;

public class RoomHandler {
    
    private DataHandler<Room> dataHandler;
    private static RoomHandler _instance;
    
    private RoomHandler(RoomRepository roomRepository){
        this.dataHandler = new DataHandler<Room>(roomRepository);
    }

    public static RoomHandler instance(RoomRepository roomRepository){
        if(_instance == null){
            _instance = new RoomHandler(roomRepository);
        }
        return _instance;
    }

    public DataResponse save(String body){
        Room room;
        try{
            room = new Room(body);
        } catch(Exception e){
            return new DataResponse(e);
        }
        return dataHandler.save(room);
    }

    public DataResponse findAll(){
        return dataHandler.findAll();
    }

    public DataResponse update(String body){
        Room room;
        try{
            room = new Room(body);
        } catch(Exception e){
            return new DataResponse(e);
        }
        return dataHandler.update(room);
    }

    public DataResponse delete(Integer id){
        return dataHandler.delete(id);
    }
}
