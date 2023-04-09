package com.example.demo.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.demo.Publisher;
import com.example.demo.Subscriber;
import com.example.demo.models.DataModel;
import com.example.demo.models.WaitingPerson;

public class WaitingListHandler extends Publisher{
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

    public String add(String body){

        WaitingPerson waitingPerson = new WaitingPerson(body);
        this.addSubscriber(waitingPerson);
        return "";
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
        List<Subscriber> subscribers = this.getSubscribers();
        String result = "Can use the desk:\n";
        Iterator<Subscriber> it = subscribers.iterator();
        while(it.hasNext()){
            WaitingPerson waitingPerson = (WaitingPerson) it.next();
            result += "person " + waitingPerson.getId() + " : " + waitingPerson.getCanUseDesk() + "\n"; 
        }
        return result;
    }
}
