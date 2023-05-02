package com.example.demo.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.demo.WaitingListPublisher;
import com.example.demo.WaitingListSubscriber;
import com.example.demo.models.DataResponse;
import com.example.demo.models.WaitingPerson;

public class WaitingListHandler extends WaitingListPublisher{
    private static WaitingListHandler _instance;
    private List<WaitingListSubscriber> subscribers;

    private WaitingListHandler(){
        this.subscribers = new ArrayList<WaitingListSubscriber>();
    }

    @Override
    public void addSubscriber(WaitingListSubscriber subscriber){
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(WaitingListSubscriber subscriber){
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Integer value){
        subscribers.forEach(subscriber->{
            subscriber.update(value);
        });
    }

    @Override
    public List<WaitingListSubscriber> getSubscribers(){
        return this.subscribers;
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
    
    public DataResponse setDeskStatus(Integer deskId, String status){
        if(status == "OK"){
            this.notifySubscribers(deskId);
        }
        return new DataResponse("Success", "deskId: " + deskId + " is available" );
    }

    public DataResponse checKStatus(String body){
        return DataResponse.success("???");
    }

    public DataResponse checkPersons(){
        List<WaitingListSubscriber> subscribers = this.getSubscribers();
        String result = "Can use the desk:\n";
        Iterator<WaitingListSubscriber> it = subscribers.iterator();
        while(it.hasNext()){
            WaitingPerson waitingPerson = (WaitingPerson) it.next();
            result += "person " + waitingPerson.getUserId() + " : " + waitingPerson.isDeskAvailable() + "\n"; 
        }
        return DataResponse.success(result);
    }
}
