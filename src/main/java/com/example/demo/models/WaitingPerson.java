package com.example.demo.models;

import com.example.demo.Subscriber;

public class WaitingPerson implements Subscriber{

    private String id;
    private boolean canUseDesk;

    public WaitingPerson(String id){
        this.id = id;
        this.canUseDesk = false;
    }

    public String getId(){
        return id;
    }

    public boolean getCanUseDesk(){
        return canUseDesk;
    }

    @Override
    public void update(Object value) {
        boolean status = (boolean) value;   
        this.canUseDesk = status;
        System.out.println(id + ": update was called");
        if(canUseDesk){
            System.out.println("user can use the desk");
        } else {
            System.out.println("user can't use the desk");
        }
    }
    
}
