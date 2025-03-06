package com.adityarastogi.lumiJournal.controller;

// import java.util.*;

import org.springframework.web.bind.annotation.RestController;

// import com.adityarastogi.lumiJournal.entity.JournalEntry;

// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/_journal")
public class JournalEntryController {
    // private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    // @GetMapping
    // public List<JournalEntry> getAll(){
    //     return new ArrayList<>(journalEntries.values());
    // }
    
    // @PostMapping
    // public boolean createEntry(@RequestBody JournalEntry myEntry){
    //     journalEntries.put(myEntry.getId(), myEntry);
    //     return true;
    // }

    // @GetMapping("id/{myId}")
    // public JournalEntry getJournalEntryById(@PathVariable Long myId){
    //     return journalEntries.get(myId);
    // }

    // @DeleteMapping("id/{myId}")
    // public JournalEntry deleteJournalEntryById(@PathVariable Long myId){
    //     return journalEntries.remove(myId);
    // }

    // @PutMapping("id/{id}")
    // public JournalEntry updateJournalEntryById(@PathVariable Long id, @RequestBody JournalEntry myEntry){
    //     return journalEntries.put(id, myEntry);
    // }
}
