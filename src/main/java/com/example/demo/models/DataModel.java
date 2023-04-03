package com.example.demo.models;

/**
 * Abstract class used for all the models which will be send in the response of an endpoint.
 */
public abstract class DataModel {

    public DataModel(){}

    /**
     * Constructor for creating an object from a JSON String
     * @param json
     */
    public DataModel(String json){}

    /**
     * Method used for getting the id of the object, required for update/delete.
     */
    public abstract Integer getId();

    /**
     * Updates the current object with the data from the object recieved as a parameter. Required for update.
     * @param dataModel
     * @throws Exception
     */
    public abstract void updateFrom(DataModel dataModel) throws Exception;

}
