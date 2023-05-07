package com.example.demo.handlers;
import java.util.Iterator;
import java.util.Map;

import com.example.demo.models.DataResponse;
import com.example.demo.models.DeskRequest;
import com.example.demo.repositories.DeskRequestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

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
            return DataResponse.error(e);
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
            return DataResponse.error(e);
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

    public DataResponse getById(Integer id){
        DataResponse data = (DataResponse)findAll();
        if(data.getStatus() == "Success"){
            Iterable<DeskRequest> deskRequests = (Iterable<DeskRequest>)data.getData();
            Iterator<DeskRequest> it = deskRequests.iterator();
            while(it.hasNext()){
                DeskRequest deskRequest = it.next();
                if(deskRequest.getDeskId() == id){
                    return DataResponse.success(deskRequest);
                }
            }
            return DataResponse.error(new Exception("desk request with desk id = " + id + " was not found"));
        }
        return data;
    }

    public DataResponse checkDeskStatus(String body){
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map;
        Integer id =  -1; 
        try{
            map = mapper.readValue(body, Map.class);
        id = (Integer) map.get("desk_id");
        } catch(Exception e){
            return DataResponse.error(e);
        }
        DataResponse data = (DataResponse)findAll();
        if(data.getStatus() == "Success"){
            Iterable<DeskRequest> deskRequests = (Iterable<DeskRequest>)data.getData();
            Iterator<DeskRequest> it = deskRequests.iterator();
            while(it.hasNext()){
                DeskRequest deskRequest = it.next();
                if(deskRequest.getDeskId() == id){
                    return DataResponse.success(deskRequest.getStatus());
                }
            }
            return DataResponse.success( "AVAILABLE");
        }
        return data;
    }
}


//i need to rethink the logic a little bit...
//reorganize the classes/data structures and meke all work properly
// use enums/static methods to create Status data