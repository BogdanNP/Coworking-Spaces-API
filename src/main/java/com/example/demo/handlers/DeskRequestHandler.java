package com.example.demo.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.example.demo.models.DataResponse;
import com.example.demo.models.DataResponseStatus;
import com.example.demo.models.Desk;
import com.example.demo.models.DeskRequest;
import com.example.demo.models.DeskRequestStatus;
import com.example.demo.models.DeskStatus;
import com.example.demo.repositories.DeskRepository;
import com.example.demo.repositories.DeskRequestRepository;

public class DeskRequestHandler {

    private DataHandler<DeskRequest> dataHandler;
    private DataHandler<Desk> deskDataHandler;
    private static DeskRequestHandler _instance;
    
    private DeskRequestHandler(DeskRequestRepository deskRequestRepository, DeskRepository deskRepository){
        this.dataHandler = new DataHandler<DeskRequest>(deskRequestRepository);
        this.deskDataHandler = new DataHandler<Desk>(deskRepository);
    }

    /**
     * Returns a single instance of the class, which can handle all the logic.
     * @param deskRequestRepository
     * @return DeskRequestHandler (_instance)
     */
    public static DeskRequestHandler instance(DeskRequestRepository deskRequestRepository, DeskRepository deskRepository){
        if(_instance == null){
            _instance = new DeskRequestHandler(deskRequestRepository, deskRepository);
        }
        return _instance;
    }

      /**
     * Creates a desk request from a JSON String and saves the DeskRequest object in the DB.
     * @param body
     * @return DataResponse
     */
    public DataResponse save(String body){
        DeskRequest deskRequest;
        try{
            deskRequest = new DeskRequest(body);
            DataResponse allDeskRequests = findAll();
            if(allDeskRequests.getStatus() == DataResponseStatus.SUCCESS){
                Iterator<DeskRequest> it = ((Iterable<DeskRequest>) allDeskRequests.getData()).iterator();
                while(it.hasNext()){
                    DeskRequest dataModel = it.next();
                    if(dataModel.getDeskId().equals(deskRequest.getId())){
                        if(dataModel.getStatus().equals(DeskRequestStatus.FUTURE) ||
                            dataModel.getStatus().equals(DeskRequestStatus.CURRENT)){
                        if((dataModel.getStartDate().compareTo(deskRequest.getStartDate()) >=0 &&
                            dataModel.getStartDate().compareTo(deskRequest.getEndDate()) <= 0) || 
                            (dataModel.getEndDate().compareTo(deskRequest.getStartDate()) >=0 &&
                            dataModel.getEndDate().compareTo(deskRequest.getEndDate()) <= 0)){
                                return DataResponse.error("Cannot create desk request in the interval selected");
                            }
                        }
                    } 
                }
                if(deskRequest.getStatus().equals(DeskRequestStatus.CURRENT)){
                    Desk desk = new Desk();
                    desk.setId(deskRequest.getDeskId());
                    desk.setStatus(DeskStatus.RESERVED);
                    deskDataHandler.update(desk);
                }
                return dataHandler.save(deskRequest);
            }
            return DataResponse.error(allDeskRequests.getMessage());
        }catch(Exception e){
            return DataResponse.error(e);
        }
    }

    /**
     * Returns all the desk requests.
     * @return DataResponse
     */
    public DataResponse findAll(){
        return dataHandler.findAll();
    }

    /**
     * Creates a DeskRequest from a JSON String and updates the DeskRequest object in the DB.
     * @param body
     * @return DataResponse
     */
    public DataResponse update(String body){
        DeskRequest deskRequest;
        try{
            deskRequest = new DeskRequest(body);
            DataResponse allDeskRequests = findAll();
            Date currentDate = new Date();               
            Desk desk = (Desk) deskDataHandler.findById(deskRequest.getDeskId()).getData();
           
            if(allDeskRequests.getStatus() == DataResponseStatus.SUCCESS){
                Iterator<DeskRequest> it = ((Iterable<DeskRequest>) allDeskRequests.getData()).iterator();
                while(it.hasNext()){
                    DeskRequest dataModel = it.next();
                    if(dataModel.getId().equals(deskRequest.getId())){
                        if(dataModel.getStatus().equals(DeskRequestStatus.CURRENT)){
                            if(deskRequest.getStatus().equals(DeskRequestStatus.FINISHED)){
                                desk.setStatus(DeskStatus.AVAILABLE); 
                            }
                        }
                    }
                    if( !(dataModel.getId().equals(deskRequest.getId())) &&
                        dataModel.getDeskId().equals(deskRequest.getDeskId())){
                        if(dataModel.getStatus().equals(DeskRequestStatus.FUTURE) ||
                            dataModel.getStatus().equals(DeskRequestStatus.CURRENT)){
                        if((dataModel.getStartDate().compareTo(deskRequest.getStartDate()) >=0 &&
                            dataModel.getStartDate().compareTo(deskRequest.getEndDate()) <= 0) || 
                            (dataModel.getEndDate().compareTo(deskRequest.getStartDate()) >=0 &&
                            dataModel.getEndDate().compareTo(deskRequest.getEndDate()) <= 0)){
                                return DataResponse.error("Cannot update desk request with the interval selected");
                            }
                        }
                    } 
                }
                if(deskRequest.getStatus().equals(DeskRequestStatus.CURRENT)){
                    if(deskRequest.getStartDate().compareTo(currentDate) <= 0 && deskRequest.getEndDate().compareTo(currentDate) >= 0){
                        desk.setStatus(DeskStatus.RESERVED);
                    }
                }
                if(deskRequest.getEndDate().compareTo(currentDate) <= 0){
                    deskRequest.setStatus(DeskRequestStatus.FINISHED);
                    desk.setStatus(DeskStatus.AVAILABLE);
                }
                deskDataHandler.update(desk);
                return dataHandler.update(deskRequest);
            }
            return DataResponse.error(allDeskRequests.getMessage());
        }catch(Exception e){
            return DataResponse.error(e);
        }
    }

    /**
     * Deletes a DeskRequest object from the DB.
     * @param id the id of the desk request wich will be deleted
     * @return DataResponse
     */
    public DataResponse delete(Integer id){
        return dataHandler.delete(id);
    }

    public DataResponse findByUserId(Integer id){
        DataResponse allDeskRequestsData = dataHandler.findAll();
        if(allDeskRequestsData.getStatus() == DataResponseStatus.SUCCESS){
            Iterable<DeskRequest> allDesks = (Iterable<DeskRequest>) allDeskRequestsData.getData();
            ArrayList<DeskRequest> userDeskRequests = new ArrayList<DeskRequest>();
            Iterator<DeskRequest> it = allDesks.iterator();
            while(it.hasNext()){
                DeskRequest d = it.next();
                if(id.equals(d.getUserId())){
                    userDeskRequests.add(d);
                }
            }
            return DataResponse.success(userDeskRequests);
        }
        return allDeskRequestsData;
    }
}