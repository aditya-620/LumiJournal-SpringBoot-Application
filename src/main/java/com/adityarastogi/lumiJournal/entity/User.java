package com.adityarastogi.lumiJournal.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;

@Document(collection = "users")
@Data
public class User {
    
    @Id
    private ObjectId id;

    // @Indexed is used to create an index on the field.
    // unique = true ensures that the field is unique.
    //but this property is not set automatically, we need to set it in application properties

    @Indexed(unique = true)
    @NonNull
    private String userName;
    
    @NonNull
    private String password;

    //@NonNull is used to ensure that the field is not null.
    //it is used to validate the field and throw an exception if the field is null.

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();

    //aap reference create kar rhe hai users collection ke ander, kiska reference create kar rhe hai? - JournalEntries ka refrence
    //yeh jo List<JournalEntry> journalEntries hai, vo refrence rakhengi , kiska - journal_entries(db) me jo entries pdi hai uska

    private List<String> roles;
}


