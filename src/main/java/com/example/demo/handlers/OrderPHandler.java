package com.example.demo.handlers;

import com.example.demo.models.DataResponse;
import com.example.demo.models.OrderP;
import com.example.demo.repositories.OrderPRepository;

public class OrderPHandler {
    
    private DataHandler<OrderP> dataHandler;
    private static OrderPHandler _instance;
    
    private OrderPHandler(OrderPRepository orderPRepository){
        this.dataHandler = new DataHandler<OrderP>(orderPRepository);
    }

    public static OrderPHandler instance(OrderPRepository orderPRepository){
        if(_instance == null){
            _instance = new OrderPHandler(orderPRepository);
        }
        return _instance;
    }

    public DataResponse save(String body){
        OrderP order;
        try{
            order = new OrderP(body);
        } catch(Exception e){
            return new DataResponse(e);
        }
        return dataHandler.save(order);
    }

    public DataResponse findAll(){
        return dataHandler.findAll();
    }

    public DataResponse update(String body){
        OrderP order;
        try{
            order = new OrderP(body);
        } catch(Exception e){
            return new DataResponse(e);
        }
        return dataHandler.update(order);
    }

    public DataResponse delete(Integer id){
        return dataHandler.delete(id);
    }
}
