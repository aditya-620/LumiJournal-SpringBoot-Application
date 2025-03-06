package com.adityarastogi.lumiJournal.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.adityarastogi.lumiJournal.entity.JournalEntry;

@Document
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
    
}
