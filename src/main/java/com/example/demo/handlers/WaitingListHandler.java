package com.example.demo.handlers;

import java.util.ArrayList;
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
    public void notifySubscribers(Integer deskId, boolean deskStatus){
        subscribers.forEach(subscriber->{
            subscriber.update(deskId, deskStatus);
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
            return DataResponse.error(e);
        } 
        this.addSubscriber(waitingPerson);
        return DataResponse.success("You were added on the waiting list");
    }
    
    public DataResponse setDeskStatus(Integer deskId, String deskStatus){
        if(deskStatus == "OK"){
            this.notifySubscribers(deskId, true);
        } else {
            this.notifySubscribers(deskId, false);
        }
        return DataResponse.success("deskId: " + deskId + " is available" );
    }

    //we should check the desk status in the desk request -> deskRequest handler is responsible for this
    public DataResponse checKStatus(String body){
        WaitingPerson waitingPerson;
        try {
            waitingPerson = new WaitingPerson(body);
        } catch (Exception e) {
            return DataResponse.error(e);
        } 
        return DataResponse.success("???");
    }

    public DataResponse checkPersons(){
        List<WaitingListSubscriber> subscribers = this.getSubscribers();
        return DataResponse.success(subscribers);
    }
}
