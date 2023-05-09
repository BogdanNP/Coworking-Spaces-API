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

    public static DataResponse success(Object object){
        return new DataResponse(DataResponseStatus.SUCCESS, "", object);
    }

    public static DataResponse success(String message){
        return new DataResponse(DataResponseStatus.SUCCESS, message, null);
    }

    public static DataResponse success(Object object, String message){
        return new DataResponse(DataResponseStatus.SUCCESS, message, object);
    }
    
    public static DataResponse error(Exception e){
        return new DataResponse(DataResponseStatus.ERROR, e.getMessage(), null);
    }

    public static DataResponse error(String message){
        return new DataResponse(DataResponseStatus.ERROR, message, null);
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

    
  @Override 
  public boolean equals(Object obj) {
      if(obj.getClass() != DataResponse.class){
          return false;
      }
      DataResponse dataResponse = (DataResponse)obj;
      return status.equals(dataResponse.getStatus()) 
      && message.equals(dataResponse.getMessage())
      && data.equals(dataResponse.getData())
      ; 
  }

  @Override
  public String toString() {
     return "DataResponse[status="+status
     + "; message="+message
     + "; data="+data
     + "]";
  }

}
