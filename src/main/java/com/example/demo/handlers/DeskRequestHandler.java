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

    public static DeskRequestHandler instance(DeskRequestRepository deskRequestRepository){
        if(_instance == null){
            _instance = new DeskRequestHandler(deskRequestRepository);
        }
        return _instance;
    }

    public DataResponse save(String body){
        DeskRequest deskRequest;
        try{
            deskRequest = new DeskRequest(body);
        }catch(Exception e){
            return new DataResponse(e);
        }
        return dataHandler.save(deskRequest);      
    }

    public DataResponse findAll(){
        return dataHandler.findAll();
    }

    public DataResponse update(String body){
        DeskRequest deskRequest;
        try{
            deskRequest = new DeskRequest(body);
        }catch(Exception e){
            return new DataResponse(e);
        }
        return dataHandler.update(deskRequest);      
    }

    public DataResponse delete(Integer id){
        return dataHandler.delete(id);
    }
}
