package com.adityarastogi.lumiJournal.controller;
import com.adityarastogi.lumiJournal.service.EmailService;
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

    @Autowired
    private EmailService emailService;

    //@GetMapping insures that the function is mapped to this end point /health-check
    //get request to /health-check will return "OK"
    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
        
        emailService.sendEmail(user.getEmail(), "Hi, " + user.getUserName(), "Welcome to Lumi Journal! \n\n" +
                "We are excited to have you on board. \n\n" +
                "Best regards, \n" +
                "Lumi Journal Team");

    }
}
