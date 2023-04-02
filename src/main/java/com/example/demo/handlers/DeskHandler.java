package com.example.demo.handlers;

import com.example.demo.models.DataResponse;
import com.example.demo.models.Desk;
import com.example.demo.repositories.DeskRepository;

public class DeskHandler {

    private DataHandler<Desk> dataHandler;
    private static DeskHandler _instance;

    private DeskHandler(DeskRepository deskRepository){
        this.dataHandler = new DataHandler<Desk>(deskRepository);
    }

    /**
     * Returns a single instance of the class, which can handle
     * all the logic.
     * @param deskRepository
     * @return DeskHandler (_instance)
     */
    public static DeskHandler instance(DeskRepository deskRepository){
        if(_instance == null){
            _instance = new DeskHandler(deskRepository);
        }
        return _instance;
    }

    /**
     * Creates a desk from a JSON. 
     * Saves the Desk object in the DB.
     * Creates and returns DataResponse object with specific
     * status and message.
     * @param body
     * @return DataResponse
     */
    public DataResponse save(String body){
        Desk desk;
        try{
            desk = new Desk(body);
        }catch(Exception e){
            return new DataResponse(e);
        }
        return dataHandler.save(desk);
    }

    /**
     * Returns all the desks.
     * @return DataResponse, data = List of Desks,
     */
    public DataResponse findAll(){
        return dataHandler.findAll();
    }

    /**
     * Creates a desk from a JSON. 
     * Checks all the desks from the DB.
     * If a desk with the requested id is found
     * then the Desk object is updated in the DB.
     * Creates and returns DataResponse object with specific
     * status and message.
     * @param body
     * @return DataResponse
     */
    public DataResponse update(String body){
        Desk desk;
        try{
            desk = new Desk(body);
        }catch(Exception e){
            return new DataResponse(e);
        }
        return dataHandler.update(desk);
    }

    /**
     * Checks all the desks from the DB.
     * If a desk with the requested id is found
     * then the Desk object is deleted from the DB.
     * Creates and returns DataResponse object with specific
     * status and message.
     * @param id the id of the desk wich will be deleted
     * @return DataResponse
     */
    public DataResponse delete(Integer id){
        return dataHandler.delete(id);
    }
}
