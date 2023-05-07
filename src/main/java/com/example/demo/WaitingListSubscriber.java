package com.example.demo;

public interface WaitingListSubscriber {
    abstract void update(Integer deskId, boolean deskStatus);
}
