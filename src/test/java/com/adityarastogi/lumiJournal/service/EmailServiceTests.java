package com.adityarastogi.lumiJournal.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail(){
        emailService.sendEmail("adityarastogi.shaurya@gmail.com", "Testing mail sender", "HI, this is a test mail from Lumi Journal. Please ignore it.");
    }
    
}
