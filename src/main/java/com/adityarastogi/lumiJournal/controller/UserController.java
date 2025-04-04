package com.adityarastogi.lumiJournal.controller;


import org.springframework.web.bind.annotation.RestController;

import com.adityarastogi.lumiJournal.apiResponse.WeatherResponse;
import com.adityarastogi.lumiJournal.entity.User;
import com.adityarastogi.lumiJournal.repository.UserRepository;
import com.adityarastogi.lumiJournal.service.UserService;
import com.adityarastogi.lumiJournal.service.WeatherService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/user")
@Tag(name = "User APIs", description = "Read, Update, and Delete user details")
public class UserController {

   
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    

    // @GetMapping    
    // public List<User> getAllUsers(){
    //     return userService.getAll();
    // }

    

    //requestbody postman se info late hai
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
    
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deteleUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        WeatherResponse weatherResponse = weatherService.getWeather("mumbai");
        String greeting = "";
        if(greeting != null){
            greeting =  ", weather feels like " + weatherResponse.getCurrent().getFeelslike();
        } 
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);
    }
            
}
