package com.example.demo.handlers;

import com.example.demo.models.DataResponse;
import com.example.demo.models.UserP;
import com.example.demo.repositories.UserPRepository;

public class UserPHandler {
    
    private DataHandler<UserP> dataHandler;
    private static UserPHandler _instance;
    
    private UserPHandler(UserPRepository userPRepository){
        this.dataHandler = new DataHandler<UserP>(userPRepository);
    }

    public static UserPHandler instance(UserPRepository userPRepository){
        if(_instance == null){
            _instance = new UserPHandler(userPRepository);
        }
        return _instance;
    }

    public DataResponse save(String body){
        UserP userP;
        try{
            userP = new UserP(body);
        } catch(Exception e){
            return new DataResponse(e);
        }
        return dataHandler.save(userP);
    }

    public DataResponse findAll(){
        return dataHandler.findAll();
    }

    public DataResponse update(String body){
        UserP userP;
        try{
            userP = new UserP(body);
        } catch(Exception e){
            return new DataResponse(e);
        }
        return dataHandler.update(userP);
    }

    public DataResponse delete(Integer id){
        return dataHandler.delete(id);
    }
}
