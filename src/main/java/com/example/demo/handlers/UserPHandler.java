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
    
    /**
     * Returns a single instance of the class, which can handle all the logic.
     * @param userPRepository
     * @return UserPHandler (_instance)
     */
    public static UserPHandler instance(UserPRepository userPRepository){
        if(_instance == null){
            _instance = new UserPHandler(userPRepository);
        }
        return _instance;
    }

    /**
     * Creates an user from a JSON String and saves the UserP object in the DB.
     * @param body
     * @return DataResponse
     */
    public DataResponse save(String body){
        UserP userP;
        try{
            userP = new UserP(body);
        } catch(Exception e){
            return DataResponse.error(e);
        }
        return dataHandler.save(userP);
    }

    /**
     * Returns all the users.
     * @return DataResponse
     */
    public DataResponse findAll(){
        return dataHandler.findAll();
    }

    /**
     * Creates a UserP from a JSON String and updates the UserP object in the DB.
     * @param body
     * @return DataResponse
     */
    public DataResponse update(String body){
        UserP userP;
        try{
            userP = new UserP(body);
        } catch(Exception e){
            return DataResponse.error(e);
        }
        return dataHandler.update(userP);
    }

    /**
     * Deletes a UserP object from the DB.
     * @param id the id of the desk wich will be deleted
     * @return DataResponse
     */
    public DataResponse delete(Integer id){
        return dataHandler.delete(id);
    }
}
