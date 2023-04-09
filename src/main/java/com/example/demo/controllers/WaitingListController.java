package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.models.DataResponse;
import com.example.demo.handlers.WaitingListHandler;

@Controller
@RequestMapping(path="/demo")
public class WaitingListController {

    private WaitingListHandler waitingListHandler(){
        return WaitingListHandler.instance();
    }
    
    @PostMapping(path="/waiting_list/add")
    public @ResponseBody String addPersonToList (@RequestBody String body){
        return waitingListHandler().add(body);
    }

    @PostMapping(path="/waiting_list/set_status")
    public @ResponseBody String updatStatus (@RequestBody String body){
        return waitingListHandler().setDeskStatus(body);
    }

    @GetMapping(path="/waiting_list/check_status")
    public @ResponseBody String checkStatus (){
        return waitingListHandler().checKStatus();
    }

    @GetMapping(path="/waiting_list/check_persons")
    public @ResponseBody String checkPersons (){
        return waitingListHandler().checkPersons();
    }
}
