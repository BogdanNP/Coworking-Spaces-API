package com.example.demo.models;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Room extends DataModel{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private Integer width;
    private Integer length;
    private List<Integer> deskList;
    private String details;

    public Room(){}

    public Room(String json) throws JsonMappingException, JsonProcessingException {
		super(json);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json, Map.class);
        setDetails((String)map.get("details"));
        //TODO: check DB logic
        //Maybe a desk should know the room id, and the room should not know all the desks
        setDeskList((List)map.get("desk_list"));
        setWidth((Integer)map.get("width"));
        setLength((Integer)map.get("length"));
    }
    
    @Override
    public void updateFrom(DataModel dataModel){
        Room source = (Room) dataModel;
        if(source.getDetails() != null){
            setDetails(source.getDetails());
        }
        if(source.getDeskList() != null){
            setDeskList(source.getDeskList());
        }
        if(source.getWidth() != null){
            setWidth(source.getWidth());
        }
        if(source.getLength() != null){
            setLength(source.getLength());
        }
    }
    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return Integer return the width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * @return Integer return the length
     */
    public Integer getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * @return List<Integer> return the deskList
     */
    public List<Integer> getDeskList() {
        return deskList;
    }

    /**
     * @param deskList the deskList to set
     */
    public void setDeskList(List<Integer> deskList) {
        this.deskList = deskList;
    }

    /**
     * @return String return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }

}
