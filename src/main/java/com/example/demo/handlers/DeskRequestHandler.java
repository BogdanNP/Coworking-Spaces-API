package com.example.demo.handlers;

import com.example.demo.models.DataResponse;
import com.example.demo.models.DeskRequest;
import com.example.demo.repositories.DeskRequestRepository;

public class DeskRequestHandler {

    private DataHandler<DeskRequest> dataHandler;
    private static DeskRequestHandler _instance;
    
    private DeskRequestHandler(DeskRequestRepository deskRequestRepository){
        this.dataHandler = new DataHandler<DeskRequest>(deskRequestRepository);
    }

    /**
     * Returns a single instance of the class, which can handle all the logic.
     * @param deskRequestRepository
     * @return DeskRequestHandler (_instance)
     */
    public static DeskRequestHandler instance(DeskRequestRepository deskRequestRepository){
        if(_instance == null){
            _instance = new DeskRequestHandler(deskRequestRepository);
        }
        return _instance;
    }

      /**
     * Creates a desk request from a JSON String and saves the DeskRequest object in the DB.
     * @param body
     * @return DataResponse
     */
    public DataResponse save(String body){
        DeskRequest deskRequest;
        try{
            deskRequest = new DeskRequest(body);
        }catch(Exception e){
            return new DataResponse(e);
        }
        return dataHandler.save(deskRequest);      
    }

    /**
     * Returns all the desk requests.
     * @return DataResponse
     */
    public DataResponse findAll(){
        return dataHandler.findAll();
    }

    /**
     * Creates a DeskRequest from a JSON String and updates the DeskRequest object in the DB.
     * @param body
     * @return DataResponse
     */
    public DataResponse update(String body){
        DeskRequest deskRequest;
        try{
            deskRequest = new DeskRequest(body);
        }catch(Exception e){
            return new DataResponse(e);
        }
        return dataHandler.update(deskRequest);      
    }

    /**
     * Deletes a DeskRequest object from the DB.
     * @param id the id of the desk request wich will be deleted
     * @return DataResponse
     */
    public DataResponse delete(Integer id){
        return dataHandler.delete(id);
    }
}
