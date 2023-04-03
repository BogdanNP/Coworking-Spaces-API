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

    /**
     * Returns a single instance of the class, which can handle all the logic.
     * @param orderPRepository
     * @return OrderPHandler (_instance)
     */
    public static OrderPHandler instance(OrderPRepository orderPRepository){
        if(_instance == null){
            _instance = new OrderPHandler(orderPRepository);
        }
        return _instance;
    }

    /**
     * Creates an order from a JSON String and saves the OrderP object in the DB.
     * @param body
     * @return DataResponse
     */
    public DataResponse save(String body){
        OrderP order;
        try{
            order = new OrderP(body);
        } catch(Exception e){
            return new DataResponse(e);
        }
        return dataHandler.save(order);
    }

    /**
     * Returns all the orders.
     * @return DataResponse
     */
    public DataResponse findAll(){
        return dataHandler.findAll();
    }

    /**
     * Creates a OrderP from a JSON String and updates the OrderP object in the DB.
     * @param body
     * @return DataResponse
     */
    public DataResponse update(String body){
        OrderP order;
        try{
            order = new OrderP(body);
        } catch(Exception e){
            return new DataResponse(e);
        }
        return dataHandler.update(order);
    }

    /**
     * Deletes a OrderP object from the DB.
     * @param id the id of the order wich will be deleted
     * @return DataResponse
     */
    public DataResponse delete(Integer id){
        return dataHandler.delete(id);
    }
}
