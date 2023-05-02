package com.example.demo.models;

/**
 * Class used for all responses.
 */
public class DataResponse {
    private String status;
    private String message;
    private Object data;

    public DataResponse(String status, String message, Object data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public DataResponse(String status, String message){
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public DataResponse(String status, Object data){
        this.status = status;
        this.data = data;
    }

    public DataResponse(Exception e){
        this.status = "Error";
        this.message = e.getMessage();
    }

    //TODO: create class for string values AppValues -> static const String success = "Success"; 

    public static DataResponse success(String message){
        return new DataResponse("Success", message);
    }
    
    public static DataResponse error(Exception e){
        return new DataResponse("Error", e.getMessage());
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
     * @return String return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return Object return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

}
