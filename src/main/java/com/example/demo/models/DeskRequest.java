package com.example.demo.models;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public
class DeskRequest extends DataModel{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String status;
    private Integer userId;
    private Integer deskId;
    private Date startDate;
    private Date endDate;

    public DeskRequest(){}
    /**
     * Creates a DeskRequest object from a JSON String.
     * @param json 
     * @return DeskRequest
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    public DeskRequest(String json) throws JsonMappingException, JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json, Map.class);
        setId((Integer)map.get("id"));
        setStatus((String)map.get("status"));
        setUserId((Integer)map.get("user_id"));
        setDeskId((Integer)map.get("desk_id"));
        setStartDate((Date)map.get("start_date"));
        setEndDate((Date)map.get("end_date"));
    } 

    /**
     * Copies all the data from one source desk to the current object
     * @param source
     */
    @Override
    public void updateFrom(DataModel dataModel){
        DeskRequest source = (DeskRequest) dataModel;
        if(source.getStatus() != null){
            setStatus(source.getStatus());
        }
        if(source.getUserId() != null){
            setUserId(source.getUserId());
        }
        if(source.getDeskId() != null){
            setDeskId(source.getDeskId());
        }
        if(source.getStartDate() != null){
            setStartDate(source.getStartDate());
        } 
        if(source.getEndDate() != null){
            setEndDate(source.getEndDate());
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
     * @return String return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return Integer return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return Integer return the deskId
     */
    public Integer getDeskId() {
        return deskId;
    }

    /**
     * @param deskId the deskId to set
     */
    public void setDeskId(Integer deskId) {
        this.deskId = deskId;
    }

    /**
     * @return Date return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return Date return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}