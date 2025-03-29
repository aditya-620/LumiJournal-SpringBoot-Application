package com.adityarastogi.lumiJournal.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.adityarastogi.lumiJournal.entity.ConfigJournalAppEntity;

@Document
public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {
    
}


