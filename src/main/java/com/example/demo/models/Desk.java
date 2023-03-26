package com.example.demo.models;

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
    private Float tariff;
    private String tariffType;

    

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
    public Float getTariff() {
        return tariff;
    }

    /**
     * @param tariff the tariff to set
     */
    public void setTariff(Float tariff) {
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
