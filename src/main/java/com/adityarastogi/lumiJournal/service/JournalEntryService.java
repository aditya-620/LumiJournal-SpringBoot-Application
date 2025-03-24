package com.adityarastogi.lumiJournal.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adityarastogi.lumiJournal.entity.JournalEntry;
import com.adityarastogi.lumiJournal.entity.User;
import com.adityarastogi.lumiJournal.repository.JournalEntryRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user = userService.findByUserName(userName);   //getting the user
            journalEntry.setDate(LocalDateTime.now());

            JournalEntry saved = journalEntryRepository.save(journalEntry);  //.save is used to save the journal entry in the database
            user.getJournalEntries().add(saved);   //.add is used to add the journal entry to the user's journal entries
            userService.saveEntry(user);   
        } catch (Exception e) {
            log.error("Error saving journal entry", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}