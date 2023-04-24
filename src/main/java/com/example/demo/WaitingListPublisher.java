package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class WaitingListPublisher {
    private List<WaitingListSubscriber> subscribers;

    public WaitingListPublisher(){
        this.subscribers = new ArrayList<WaitingListSubscriber>();
    }

    public void addSubscriber(WaitingListSubscriber subscriber){
        subscribers.add(subscriber);
    }

    public void removeSubscriber(WaitingListSubscriber subscriber){
        subscribers.remove(subscriber);
    }

    public void notifySubscribers(boolean value){
        subscribers.forEach(subscriber->{
            subscriber.update(value);
        });
    }

    public List<WaitingListSubscriber> getSubscribers(){
        return this.subscribers;
    }
}
