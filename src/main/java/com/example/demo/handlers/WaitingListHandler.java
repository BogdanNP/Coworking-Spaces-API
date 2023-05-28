package com.example.demo.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.demo.WaitingListPublisher;
import com.example.demo.WaitingListSubscriber;
import com.example.demo.models.DataResponse;
import com.example.demo.models.DataResponseStatus;
import com.example.demo.models.Desk;
import com.example.demo.models.DeskRequest;
import com.example.demo.models.DeskRequestStatus;
import com.example.demo.models.WaitingPerson;
import com.example.demo.repositories.DeskRepository;
import com.example.demo.repositories.DeskRequestRepository;

public class WaitingListHandler extends WaitingListPublisher{
    
    private DataHandler<DeskRequest> deskRequestDataHandler;
    private DataHandler<Desk> deskDataHandler;

    private static WaitingListHandler _instance;
    private List<WaitingListSubscriber> subscribers;

    private WaitingListHandler(DeskRequestRepository deskRequestRepository, DeskRepository deskRepository){
        this.deskRequestDataHandler = new DataHandler<DeskRequest>(deskRequestRepository);
        this.deskDataHandler = new DataHandler<Desk>(deskRepository);
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

    public static WaitingListHandler instance(DeskRequestRepository deskRequestRepository, DeskRepository deskRepository){
        if (_instance == null){
            _instance = new WaitingListHandler(deskRequestRepository, deskRepository);
        }
        return _instance;
    }

    /**
     * Adds a waiting person in the waiting list.
     * @param body
     * @return
     */
    public DataResponse add(String body){
        WaitingPerson waitingPerson;
        try {
            waitingPerson = new WaitingPerson(body);
            if(subscribers.contains(waitingPerson)){
                return DataResponse.error("You are already subscribed to this desk!");
            }
            DataResponse dataResponse = this.checkDeskStatus(waitingPerson.getDeskId());
            if(dataResponse.getStatus() == DataResponseStatus.SUCCESS){
                if(dataResponse.getData() == DeskRequestStatus.FINISHED){
                    waitingPerson.setDeskAvailable(true);
                }
            }
        } catch (Exception e) {
            return DataResponse.error(e);
        } 
        this.addSubscriber(waitingPerson);
        return DataResponse.success(waitingPerson, "You were added on the waiting list");
    }

    /**
     * Removes a waiting person from the waiting list.
     * @param id
     * @return
     */
    public DataResponse remove(Integer id){
        Iterator<WaitingListSubscriber> it = this.subscribers.iterator();
        while(it.hasNext()){
            WaitingPerson waitingPerson = (WaitingPerson) it.next();
            if(waitingPerson.getUserId() == id){
                removeSubscriber(waitingPerson);
                break;
            }
        }
        return DataResponse.success("You were removed from the waiting list");
    }

    public DataResponse checkPersons(){
        List<WaitingListSubscriber> subscribers = this.getSubscribers();
        return DataResponse.success(subscribers);
    }

    /**
     * Checks if a desk exists, first in the desk requests then checks all the deska in DB.
     * If the desk is found then we call the method "notifySubscribers" to inform the
     * subscribers abut the desk status.
     * If the desk is not found then we return an error message in the DataResponse object.
     * @param id
     * @return DataResponse
     */

     //TODO: check the new logic here
    public DataResponse checkDeskStatus(Integer id){
        DataResponse deskRequestData = deskRequestDataHandler.findAll();
        if(deskRequestData.getStatus() == DataResponseStatus.SUCCESS){
            Iterable<DeskRequest> deskRequests = (Iterable<DeskRequest>)deskRequestData.getData();
            Iterator<DeskRequest> deskRequestIt = deskRequests.iterator();
            while(deskRequestIt.hasNext()){
                DeskRequest deskRequest = deskRequestIt.next();
                if(deskRequest.getDeskId() == id){
                    if(deskRequest.getStatus().compareTo(DeskRequestStatus.FINISHED) == 0){
                        notifySubscribers(id, true);
                    } else {
                        notifySubscribers(id, false);
                    }
                    return DataResponse.success(deskRequest.getStatus());
                }
            }
            DataResponse deskData = deskDataHandler.findAll();
            Iterable<Desk> desks = (Iterable<Desk>) deskData.getData();
            Iterator<Desk> desksIt = desks.iterator();
            while(desksIt.hasNext()){
                Desk desk = desksIt.next();
                if(id.equals(desk.getId())){
                    notifySubscribers(id, true);
                    return DataResponse.success(desk);
                }
            }
            return DataResponse.error("there is no desk with id = " + id);
        }
        return deskRequestData;
    }


    public DataResponse getByUserId(Integer id){
        Iterator<WaitingListSubscriber> it = subscribers.iterator();
        ArrayList<Integer> deskIds = new ArrayList<Integer>();
        while(it.hasNext()){
            WaitingPerson waitingPerson = (WaitingPerson) it.next();
            if(id.equals(waitingPerson.getUserId())){
                deskIds.add(waitingPerson.getDeskId());
            }
        }
        ArrayList<Desk> deskListResult = new ArrayList<Desk>();
        DataResponse deskData = deskDataHandler.findAll();
        Iterable<Desk> desks = (Iterable<Desk>) deskData.getData();
        Iterator<Desk> desksIt = desks.iterator();
        while(desksIt.hasNext()){
            Desk desk = desksIt.next();
            if(deskIds.contains(desk.getId())){
                deskListResult.add(desk);
            }
        }
        return DataResponse.success(deskListResult);
    }
}
