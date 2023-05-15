package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.handlers.WaitingListHandler;
import com.example.demo.models.DataResponse;
import com.example.demo.repositories.DeskRequestRepository;
import com.example.demo.repositories.DeskRepository;

@Controller
@RequestMapping(path="/demo")
public class WaitingListController {

    @Autowired 
    private DeskRequestRepository deskRequestRepository;    

    @Autowired
    private DeskRepository deskRepository;    

    private WaitingListHandler waitingListHandler(){
        return WaitingListHandler.instance(deskRequestRepository, deskRepository);
    }
    
    /**
    * Creates a new waiting person and adds it in the waiting list.
    * @param body String -> waiting person object in json format
    * @apiNote waiting person object contains the following:
    * @apiNote user_id
    * @apiNote desk_id
    * @return DataResponse (status, message).
    */
    @PostMapping(path="/waiting_list/add")
    public @ResponseBody DataResponse addPersonToList (@RequestBody String body){
        return waitingListHandler().add(body);
    }

    /**
    * Removes an user from the waiting list.
    * @param id String -> user id 
    * @return DataResponse (status, message).
    */
    @DeleteMapping(path="/waiting_list/delete")
    public @ResponseBody DataResponse removePersonFromList (@RequestParam("id") Integer id){
        return waitingListHandler().remove(id);
    }

    /**
    * Returns the status of a desk
    * @return DataResponse (status, message, status)
    */
    @PostMapping(path="/waiting_list/get_by")
    public @ResponseBody DataResponse checkStatus (@RequestBody String body){
        return waitingListHandler().checkDeskStatus(body);
    }

    /**
    * Returns all the users from in the waiting list
    * @return DataResponse (status, message, list of waiting persons)
    */
    @GetMapping(path="/waiting_list/all")
    public @ResponseBody DataResponse checkPersons (){
        return waitingListHandler().checkPersons();
    }
}
