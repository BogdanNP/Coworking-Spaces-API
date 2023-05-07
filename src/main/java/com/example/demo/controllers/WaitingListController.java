package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.handlers.DeskRequestHandler;
import com.example.demo.handlers.WaitingListHandler;
import com.example.demo.models.DataResponse;
import com.example.demo.repositories.DeskRequestRepository;

@Controller
@RequestMapping(path="/demo")
public class WaitingListController {

    @Autowired 
    private DeskRequestRepository deskRequestRepository;    

    private WaitingListHandler waitingListHandler(){
        return WaitingListHandler.instance();
    }

    
    private DeskRequestHandler deskRequestHandler(){
        return DeskRequestHandler.instance(deskRequestRepository);
    }

    @PostMapping(path="/waiting_list/add")
    public @ResponseBody DataResponse addPersonToList (@RequestBody String body){
        return waitingListHandler().add(body);
    }

    @PostMapping(path="/waiting_list/set_status")
    public @ResponseBody DataResponse updatStatus (@RequestBody String body){
        return waitingListHandler().setDeskStatus(1, body);
    }

    @GetMapping(path="/waiting_list/check_status")
    public @ResponseBody DataResponse checkStatus (@RequestBody String body){
        return deskRequestHandler().checkDeskStatus(body);
    }

    @GetMapping(path="/waiting_list/check_persons")
    public @ResponseBody DataResponse checkPersons (){
        return waitingListHandler().checkPersons();
    }
}
