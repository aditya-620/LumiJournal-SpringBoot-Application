package com.adityarastogi.lumiJournal.controller;
import com.adityarastogi.lumiJournal.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adityarastogi.lumiJournal.entity.User;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    //@GetMapping insures that the function is mapped to this end point /health-check
    //get request to /health-check will return "OK"
    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }
}
