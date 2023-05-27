package com.example.demo.handlers;

import java.util.Iterator;


import com.example.demo.models.DataResponse;
import com.example.demo.models.DataResponseStatus;
import com.example.demo.models.UserP;
import com.example.demo.models.UserPTypes;
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
            userP.setPassword("1234");
            if (_getByUsername(userP.getUsername()) != null){
                return DataResponse.error("Username already in use");
            } else {
                return dataHandler.save(userP);
            }
        } catch(Exception e){
            return DataResponse.error(e);
        }
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

    public DataResponse login(String body){
        UserP userP;
        try{
            userP = new UserP(body);
            UserP user = _getByUsername(userP.getUsername());
            if (user != null){
                if(user.getPassword().equals(userP.getPassword())){
                    return DataResponse.success(user);
                }else {
                    return DataResponse.error("Incorrect password");   
                }
            }
            return DataResponse.error("Username does not exist");
        } catch(Exception e){
            return DataResponse.error(e);
        }
    }

    public DataResponse register(String body){
        UserP userP;
        try{
            userP = new UserP(body);
            userP.setType(UserPTypes.LOGGED_IN);
            if (_getByUsername(userP.getUsername()) != null){
                return DataResponse.error("Username already in use");
            } else {
                return dataHandler.save(userP);
            }
        } catch(Exception e){
            return DataResponse.error(e);
        }        
    }

    private UserP _getByUsername(String username) throws Exception{
        DataResponse allUsers = dataHandler.findAll();
        if (allUsers.getStatus() == DataResponseStatus.SUCCESS){
            Iterable<UserP> users = (Iterable<UserP>) allUsers.getData();
            Iterator<UserP> it = users.iterator();
            while(it.hasNext()){
                UserP user = it.next();
                if(user.getUsername().equals(username)){
                    return user;
                }
            }
            return null;
        }
        throw new Exception(allUsers.getMessage());
    } 
}
