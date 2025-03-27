package com.adityarastogi.lumiJournal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.adityarastogi.lumiJournal.entity.User;
import com.adityarastogi.lumiJournal.repository.UserRepository;

// @SpringBootTest annotation tells Spring Boot to look for a main configuration class (one with @SpringBootApplication, for instance) and use that to start a Spring application context.
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;
    
    @Disabled
    @Test
    public void testFindByUserName(){
        // assertEquals(4, 2+2);
        User user = userRepository.findByUserName("shaurya");
        assertNotNull(user);
        assertTrue(!user.getJournalEntries().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
        "1,1,2",
        "2,10,12",
        "3,3,9"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }
}
