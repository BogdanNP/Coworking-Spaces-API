package com.example.demo;

import java.util.List;

public abstract class WaitingListPublisher {

    public abstract void addSubscriber(WaitingListSubscriber subscriber);

    public abstract void removeSubscriber(WaitingListSubscriber subscriber);

    public abstract void notifySubscribers(Integer deskId, boolean deskStatus);

    public abstract List<WaitingListSubscriber> getSubscribers();
}
