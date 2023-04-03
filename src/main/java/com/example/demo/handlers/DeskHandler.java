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
     * Returns a single instance of the class, which can handle all the logic.
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
     * Creates a desk from a JSON String and saves the Desk object in the DB.
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
     * @return DataResponse
     */
    public DataResponse findAll(){
        return dataHandler.findAll();
    }

    /**
     * Creates a Desk from a JSON String and updates the Desk object in the DB.
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
     * Deletes a Desk object from the DB.
     * @param id the id of the desk wich will be deleted
     * @return DataResponse
     */
    public DataResponse delete(Integer id){
        return dataHandler.delete(id);
    }
}
