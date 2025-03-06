package com.adityarastogi.lumiJournal.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//A POJO class, or Plain Old Java Object class, is a Java class that is not restricted by any special frameworks or conventions. 
//POJO classes are popular because they are easy to read and write, and they promote simplicity and maintainability

@Document(collection = "journal_entries")
public class JournalEntry {

    @Id
    private ObjectId id;
    
    private String title;
    private String content;
    private LocalDateTime date;

    

    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
