package com.example.demo.models;

public abstract class DataModel {
    
    // public static DataModel fromJson(String json){
    //     return null;
    // };

    public DataModel(){}

    public DataModel(String json){}

    public abstract Integer getId();

    public abstract void updateFrom(DataModel dataModel) throws Exception;

}
