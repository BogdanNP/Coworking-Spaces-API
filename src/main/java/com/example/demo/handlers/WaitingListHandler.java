package com.example.demo.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.demo.WaitingListPublisher;
import com.example.demo.WaitingListSubscriber;
import com.example.demo.models.DataModel;
import com.example.demo.models.DataResponse;
import com.example.demo.models.WaitingPerson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class WaitingListHandler extends WaitingListPublisher{
    private static WaitingListHandler _instance;
    private WaitingListHandler(){
        super();
    }

    /*
     * TODOs:
     * - make person to wait for a Desk
     * - notify person when they can use a Desk
     * - Do this for more desks
     */

    public static WaitingListHandler instance(){
        if (_instance == null){
            _instance = new WaitingListHandler();
        }
        return _instance;
    }

    public DataResponse add(String body){

        WaitingPerson waitingPerson;
        try {
            waitingPerson = new WaitingPerson(body);
        } catch (Exception e) {
            return new DataResponse(e);
        } 
        this.addSubscriber(waitingPerson);
        return new DataResponse("Success", "You were added on the waiting list");
    }
    
    public boolean deskStatus = false;

    public String setDeskStatus(String status){
        this.deskStatus = !this.deskStatus;
        this.notifySubscribers(deskStatus);
        return "desk status = " + this.deskStatus;
    }

    public String checKStatus(){
        return "desk status = " + this.deskStatus;
    }

    public String checkPersons(){
        List<WaitingListSubscriber> subscribers = this.getSubscribers();
        String result = "Can use the desk:\n";
        Iterator<WaitingListSubscriber> it = subscribers.iterator();
        while(it.hasNext()){
            WaitingPerson waitingPerson = (WaitingPerson) it.next();
            result += "person " + waitingPerson.getUserId() + " : " + waitingPerson.getCanUseDesk() + "\n"; 
        }
        return result;
    }
}
