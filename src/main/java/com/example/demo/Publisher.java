package com.example.demo;

import java.util.List;

public abstract class Publisher {
    private List<Subscriber> subscribers;

    void addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    void removeSubscriber(Subscriber subscriber){
        subscribers.remove(subscriber);
    }

    void notifySubscribers(){
        subscribers.forEach(subscriber->{
            subscriber.update();
        });
    }
}
