package com.adityarastogi.lumiJournal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    //@GetMapping insures that the function is mapped to this end point /health-check
    //get request to /health-check will return "OK"
    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }
}
