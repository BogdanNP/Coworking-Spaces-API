package com.example.demo.handlers;

import java.util.Iterator;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.DataModel;
import com.example.demo.models.DataResponse;

public class DataHandler<T extends DataModel> {

    private CrudRepository<T, Integer> repository;

    public DataHandler(CrudRepository<T, Integer> repository){
        this.repository = repository;
    }
    
    /**
     * Saves the T object in the DB.
     * @param dataModel
     * @return DataResponse (status: Success or Error)
     */
    public DataResponse save(T dataModel){
        try{
            repository.save(dataModel);
        }catch(Exception e){
            DataResponse dataResponse = new DataResponse("Error", e.getMessage());
            return dataResponse;
        }
        DataResponse dataResponse = new DataResponse("Success", "Created.");
        return dataResponse;        
    }
    
      /**
     * Returns all the T objects from the DB.
     * @return DataResponse (status: Success or Error)
     */
    public DataResponse findAll(){
        try{
            Iterable<T> dataModels = repository.findAll();
            DataResponse dataResponse = new DataResponse("Success", dataModels);
            return dataResponse;
        }catch(Exception e){
            DataResponse dataResponse = new DataResponse("Error", e.getMessage());
            return dataResponse;
        }
    }

     /**
     * Checks all the T objects from the DB, if an object with the requested id is found
     * then the T object is updated in the DB.
     * @param dataModelToUpdate
     * @return DataResponse (status: Success or Error)
     */
    public DataResponse update(T dataModelToUpdate){
        try{

            //TODO: update this method in order to keep the same id;

            Iterable<T> dataModels =  repository.findAll();
            boolean found = false;
            Iterator<T> it = dataModels.iterator();
            while(it.hasNext()){
                T dataModel = it.next();
                if(dataModel.getId().equals(dataModelToUpdate.getId())){
                    found = true;
                    repository.delete(dataModel);
                    dataModel.updateFrom(dataModelToUpdate);
                    repository.save(dataModel);
                }
            }
            if(found){
                return new DataResponse("Success", "Updated.");
            }
            return new DataResponse("Error", "id " + dataModelToUpdate.getId() +" was not found.");
        }catch(Exception e){
            return new DataResponse("Error", e.getMessage());
        }
    }

     /**
     * Checks all the T objects from the DB, if the object with the requested id is found
     * then the object is deleted from the DB.
     * @param id the id of the object wich will be deleted
     * @return DataResponse
     */
    public DataResponse delete(Integer id){
        try{
            Iterable<T> dataModels = repository.findAll();
            boolean found = false;
            Iterator<T> it = dataModels.iterator();
            while(it.hasNext()){
                T dataModel = it.next();
                if(dataModel.getId().equals(id)){
                    found = true;
                    repository.delete(dataModel);
                }
            }
            if(found){
                return new DataResponse("Suuccess", "Deleted.");
            }
            return new DataResponse("Error", "id " + id +" was not found.");
        } catch (Exception e){
            return new DataResponse("Error", e.getMessage());
        }
    }
}
