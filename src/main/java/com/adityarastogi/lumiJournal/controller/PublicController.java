package com.adityarastogi.lumiJournal.controller;
import com.adityarastogi.lumiJournal.service.EmailService;
import com.adityarastogi.lumiJournal.service.UserService;
import com.adityarastogi.lumiJournal.utils.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adityarastogi.lumiJournal.entity.User;

@RestController
@Slf4j
@RequestMapping("/public")
@Tag(name = "Public APIs", description = "Sign up and login")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    //@GetMapping insures that the function is mapped to this end point /health-check
    //get request to /health-check will return "OK"
    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }

    // @PostMapping("/signup")
    // public void createUser(@RequestBody User user){
    //     userService.saveNewUser(user);
        
    //     emailService.sendEmail(user.getEmail(), "Hi, " + user.getUserName(), "Welcome to Lumi Journal! \n\n" +
    //             "We are excited to have you on board. \n\n" +
    //             "Best regards, \n" +
    //             "Lumi Journal Team");

    // }

    @PostMapping("/signup")
    @Operation(summary = "Sign up to the application")
    public void signup(@RequestBody com.adityarastogi.lumiJournal.dto.UserDTO user) {
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setSentimentAnalysis(user.isSentimentAnalysis());
        userService.saveNewUser(newUser);

        emailService.sendEmail(user.getEmail(), "Hi, " + user.getUserName(), "Welcome to Lumi Journal! \n\n" +
                "We are excited to have you on board. \n\n" +
                "Best regards, \n" +
                "Lumi Journal Team");
    }

    @PostMapping("/login")
    @Operation(summary = "Login to the application")
    public ResponseEntity<String> login(@RequestBody User user) {
        try{
            authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName()); 
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } 
        
        catch (Exception e) {
            log.error("Exception occured while createAuthenticationToken", e);
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }


}
