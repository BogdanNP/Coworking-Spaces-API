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

    /**
     * Returns a single instance of the class, which can handle all the logic.
     * @param deskRepository
     * @return RoomHandler (_instance)
     */
    public static RoomHandler instance(RoomRepository roomRepository){
        if(_instance == null){
            _instance = new RoomHandler(roomRepository);
        }
        return _instance;
    }

    /**
     * Creates a room from a JSON String and saves the Room object in the DB.
     * @param body
     * @return DataResponse
     */
    public DataResponse save(String body){
        Room room;
        try{
            room = new Room(body);
        } catch(Exception e){
            return new DataResponse(e);
        }
        return dataHandler.save(room);
    }

     /**
     * Returns all the rooms.
     * @return DataResponse
     */
    public DataResponse findAll(){
        return dataHandler.findAll();
    }

    /**
     * Creates a Room from a JSON String and updates the Room object in the DB.
     * @param body
     * @return DataResponse
     */
    public DataResponse update(String body){
        Room room;
        try{
            room = new Room(body);
        } catch(Exception e){
            return new DataResponse(e);
        }
        return dataHandler.update(room);
    }

    /**
     * Deletes a Room object from the DB.
     * @param id the id of the desk wich will be deleted
     * @return DataResponse
     */
    public DataResponse delete(Integer id){
        return dataHandler.delete(id);
    }
}
