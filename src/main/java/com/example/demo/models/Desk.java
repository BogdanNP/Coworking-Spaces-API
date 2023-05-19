package com.example.demo.models;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Desk extends DataModel{
    @Id
    // @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private Integer width;
    private Integer height;
    private Integer length;
    private Double tariff;
    private String tariffType;
    private String status;
    private Integer roomId;

    public Desk(){}

    public Desk copy(){
        Desk desk = new Desk();
        desk.setId(id);
		desk.setHeight(height);
		desk.setWidth(width);
		desk.setLength(length);
		desk.setTariff(tariff);
		desk.setTariffType(tariffType);
		desk.setStatus(status);
		desk.setRoomId(roomId);
        return desk;
    }

    /**
     * Creates a Desk object from a JSON String.
     * @param json 
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    public Desk (String json) throws JsonMappingException, JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json, Map.class);
        setId((Integer)map.get("id"));
        setWidth((Integer)map.get("width"));
        setHeight((Integer)map.get("height"));
        setLength((Integer)map.get("length"));
        setTariff((Double)map.get("tariff"));
        setTariffType((String)map.get("tariff_type"));
        setStatus((String)map.get("status"));
        setRoomId((Integer)map.get("room_id"));
    } 

    /**
     * Copies all the data from one source desk to the current object.
     * @param source
     */
    @Override
    public void updateFrom(DataModel dataModel){
        Desk source = (Desk) dataModel;
        if(source.getWidth() != null){
            setWidth(source.getWidth());
        }
        if(source.getHeight() != null){
            setHeight(source.getHeight());
        }
        if(source.getLength() != null){
            setLength(source.getLength());
        }
        if(source.getTariff() != null){
            setTariff(source.getTariff());
        } 
        if(source.getTariffType() != null){
            setTariffType(source.getTariffType());
        }
        if(source.getRoomId() != null){
            setRoomId(source.getRoomId());
        }  
        if(source.getStatus() != null){
            setStatus(source.getStatus());
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
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return Double return the width
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
     * @return Integer return the height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(Integer height) {
        this.height = height;
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
     * @return Float return the tariff
     */
    public Double getTariff() {
        return tariff;
    }

    /**
     * @param tariff the tariff to set
     */
    public void setTariff(Double tariff) {
        this.tariff = tariff;
    }

    /**
     * @return String return the tariffType
     */
    public String getTariffType() {
        return tariffType;
    }

    /**
     * @param tariffType the tariffType to set
     */
    public void setTariffType(String tariffType) {
        this.tariffType = tariffType;
    }

    @Override 
    public boolean equals(Object obj) {
        if(obj != null && obj.getClass() != Desk.class){
            return false;
        }
        Desk desk = (Desk)obj;
        return id.equals(desk.getId()) 
        && height.equals(desk.getHeight())
        && width.equals(desk.getWidth())
        && length.equals(desk.getLength()) 
        && tariff.equals(desk.getTariff()) 
        && roomId.equals(desk.getRoomId()) 
        && status.equals(desk.getStatus()) 
        && tariffType.equals(desk.getTariffType()); 
    }

    @Override
    public String toString() {
       return "Desk[id="+id
       + "; height="+height
       + "; width="+width
       + "; length="+length
       + "; tariff="+tariff
       + "; tariffType="+tariffType
       + "; roomId="+roomId
       + "; status="+status
       + "]";
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
     * @return Integer return the roomId
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * @param roomId the roomId to set
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

}
