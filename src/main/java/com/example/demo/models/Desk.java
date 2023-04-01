package com.example.demo.models;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Desk {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private Integer width;
    private Integer height;
    private Integer length;
    private Double tariff;
    private String tariffType;

    /**
     * Creates a Desk object from a JSON String.
     * @param json 
     * @return Desk
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    public static Desk fromJson(String json) throws JsonMappingException, JsonProcessingException{
        Desk desk = new Desk();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json, Map.class);
        desk.setId((Integer)map.get("id"));
        desk.setWidth((Integer)map.get("width"));
        desk.setHeight((Integer)map.get("height"));
        desk.setLength((Integer)map.get("length"));
        desk.setTariff((Double)map.get("tariff"));
        desk.setTariffType((String)map.get("tariff_type"));
        return desk;
    } 

    /**
     * Copies all the data from one source desk to the current object
     * @param source
     */
    public void updateFrom(Desk source){
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

}
