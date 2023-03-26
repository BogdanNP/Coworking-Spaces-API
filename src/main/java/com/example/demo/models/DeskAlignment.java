package com.example.demo.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class DeskAlignment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer deskId;
    private Integer positionX;
    private Integer positionY;
    private String orientation;

    

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
     * @return Integer return the positionX
     */
    public Integer getPositionX() {
        return positionX;
    }

    /**
     * @param positionX the positionX to set
     */
    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    /**
     * @return Integer return the positionY
     */
    public Integer getPositionY() {
        return positionY;
    }

    /**
     * @param positionY the positionY to set
     */
    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    /**
     * @return String return the orientation
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * @param orientation the orientation to set
     */
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

}
