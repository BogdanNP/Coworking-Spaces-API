package com.example.demo.models;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.demo.WaitingListSubscriber;

public class WaitingPerson implements WaitingListSubscriber{

    private Integer userId;
    private Integer deskId;
    private boolean deskAvailable;

    public WaitingPerson(String json) throws JsonMappingException, JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json, Map.class);
        setUserId((Integer) map.get("user_id"));
        setDeskId((Integer) map.get("desk_id"));
        setDeskAvailable(false);
    }

 
    @Override
    public void update(Integer deskId, boolean deskStatus) {
        if(this.deskId == deskId){
            this.setDeskAvailable(deskStatus);
        }
    }
    
    public Integer getUserId(){
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDeskId(){
        return deskId;
    }


    /**
     * @param deskId the deskId to set
     */
    public void setDeskId(Integer deskId) {
        this.deskId = deskId;
    }

    /**
     * @return boolean return the canUseDesk
     */
    public boolean isDeskAvailable() {
        return deskAvailable;
    }

    /**
     * @param canUseDesk the canUseDesk to set
     */
    public void setDeskAvailable(boolean deskAvailable) {
        this.deskAvailable = deskAvailable;
    }

}
