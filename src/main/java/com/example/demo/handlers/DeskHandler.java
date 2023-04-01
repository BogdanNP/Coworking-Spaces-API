package com.example.demo.handlers;


import java.util.Iterator;

import com.example.demo.models.DataResponse;
import com.example.demo.models.Desk;
import com.example.demo.repositories.DeskRepository;

public class DeskHandler {

    private DeskRepository deskRepository;
    
    private static DeskHandler _instance;

    private DeskHandler(DeskRepository deskRepository){
        this.deskRepository = deskRepository;
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
        try{
            deskRepository.save(Desk.fromJson(body));
        }catch(Exception e){
            DataResponse dataResponse = new DataResponse("Error", e.getMessage());
            return dataResponse;
        }
        DataResponse dataResponse = new DataResponse("Success", "Desk created.");
        return dataResponse;        
    }

    /**
     * Returns all the desks.
     * @return DataResponse, data = List of Desks,
     */
    public DataResponse findAll(){
        try{
            Iterable<Desk> desks =  deskRepository.findAll();
            DataResponse dataResponse = new DataResponse("Success", desks);
            return dataResponse;
        }catch(Exception e){
            DataResponse dataResponse = new DataResponse("Error", e.getMessage());
            return dataResponse;
        }
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
        try{

            //TODO: update this method in order to keep the same id;

            Desk deskToUpdate = Desk.fromJson(body);
            Iterable<Desk> desks =  deskRepository.findAll();
            boolean found = false;
            Iterator<Desk> it = desks.iterator();
            while(it.hasNext()){
                Desk desk = it.next();
                if(desk.getId().equals(deskToUpdate.getId())){
                    found = true;
                    deskRepository.delete(desk);
                    desk.updateFrom(deskToUpdate);
                    deskRepository.save(desk);
                }
            }
            if(found){
                return new DataResponse("Success", "Desk updated.");
            }
            return new DataResponse("Error", "Desk with id = " + deskToUpdate.getId() +" was not found.");
        }catch(Exception e){
            return new DataResponse("Error", e.getMessage());
        }
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
        try{
            Iterable<Desk> desks = deskRepository.findAll();
            boolean found = false;
            Iterator<Desk> it = desks.iterator();
            while(it.hasNext()){
                Desk desk = it.next();
                if(desk.getId().equals(id)){
                    found = true;
                    deskRepository.delete(desk);
                }
            }
            if(found){
                return new DataResponse("Suuccess", "Desk deleted.");
            }
            return new DataResponse("Error", "Desk with id = " + id +" was not found.");
        } catch (Exception e){
            return new DataResponse("Error", e.getMessage());
        }
    }
}
