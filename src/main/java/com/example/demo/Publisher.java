package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private List<Subscriber> subscribers;

    public Publisher(){
        this.subscribers = new ArrayList<Subscriber>();
    }

    public void addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber){
        subscribers.remove(subscriber);
    }

    public void notifySubscribers(Object value){
        subscribers.forEach(subscriber->{
            subscriber.update(value);
        });
    }

    public List<Subscriber> getSubscribers(){
        return this.subscribers;
    }
}
