package com.example.demo.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.example.demo.WaitingListPublisher;
import com.example.demo.WaitingListSubscriber;
import com.example.demo.models.DataResponse;
import com.example.demo.models.DataResponseStatus;
import com.example.demo.models.Desk;
import com.example.demo.models.DeskRequest;
import com.example.demo.models.DeskStatus;
import com.example.demo.models.WaitingPerson;
import com.example.demo.repositories.DeskRepository;
import com.example.demo.repositories.DeskRequestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WaitingListHandler extends WaitingListPublisher{

    // TODO: add javadoc
    
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

    public DataResponse add(String body){
        WaitingPerson waitingPerson;
        try {
            waitingPerson = new WaitingPerson(body);
            DataResponse dataResponse = this._checkDeskById(waitingPerson.getDeskId());
            if(dataResponse.getStatus() == DataResponseStatus.SUCCESS){
                if(dataResponse.getMessage() == DeskStatus.AVAILABLE){
                    waitingPerson.setDeskAvailable(true);
                }
            }
        } catch (Exception e) {
            return DataResponse.error(e);
        } 
        this.addSubscriber(waitingPerson);
        return DataResponse.success(waitingPerson, "You were added on the waiting list");
    }

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

    public DataResponse checkDeskStatus(String body){
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map;
        Integer id =  -1; 
        try{
            map = mapper.readValue(body, Map.class);
        } catch(Exception e){
            return DataResponse.error(e);
        }
        id = (Integer) map.get("desk_id");
        return this._checkDeskById(id);
    }

    private DataResponse _checkDeskById(Integer id){
        DataResponse deskRequestData = deskRequestDataHandler.findAll();
        if(deskRequestData.getStatus() == DataResponseStatus.SUCCESS){
            Iterable<DeskRequest> deskRequests = (Iterable<DeskRequest>)deskRequestData.getData();
            Iterator<DeskRequest> deskRequestIt = deskRequests.iterator();
            while(deskRequestIt.hasNext()){
                DeskRequest deskRequest = deskRequestIt.next();
                if(deskRequest.getDeskId() == id){
                    if(deskRequest.getStatus().compareTo(DeskStatus.AVAILABLE) == 0){
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
                if(desk.getId() == id){
                    notifySubscribers(id, true);
                    return DataResponse.success(DeskStatus.AVAILABLE);
                }
            }
            return DataResponse.error("there is no desk with id = " + id);
        }
        return deskRequestData;
    }

  
}
