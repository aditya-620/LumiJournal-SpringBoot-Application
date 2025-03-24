package com.adityarastogi.lumiJournal.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;


//A POJO class, or Plain Old Java Object class, is a Java class that is not restricted by any special frameworks or conventions. 
//POJO classes are popular because they are easy to read and write, and they promote simplicity and maintainability

// @Getter and @Setter are annotations provided by Project Lombok. These annotations are used to generate getters and setters for the fields in the class.
// @Getter
// @Setter
@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {

    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
    
}
