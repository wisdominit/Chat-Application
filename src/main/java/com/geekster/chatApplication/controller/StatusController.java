package com.geekster.chatApplication.controller;

import com.geekster.chatApplication.model.Status;
import com.geekster.chatApplication.service.StatusService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class StatusController {
    @Autowired
    StatusService statusService;
    @PostMapping("/status")
    public ResponseEntity<String> createStatus(@RequestBody String statusData){
        Status status= setStatus(statusData);
        int id = statusService.saveStatus(status);
        return new ResponseEntity<>("The status with code: "+id+" has been created",HttpStatus.OK);
    }
    private Status setStatus(String statusData){
        Status status = new Status();
        JSONObject json = new JSONObject(statusData);
        String statusName = json.getString("statusName");
        String statusDescription = json.getString("statusDescription");
        status.setStatusName(statusName);
        status.setStatusDescription(statusDescription);
        return status;
    }
}
