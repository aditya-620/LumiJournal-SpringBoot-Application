package com.adityarastogi.lumiJournal.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.adityarastogi.lumiJournal.entity.User;

public class UserRepositoryImpl {
    

    @Autowired
    private MongoTemplate mongoTemplate;

    // Custom methods can be implemented here using MongoTemplate
    // For example, you can create a method to find users by their email address

    public List<User> getUserForSA(){
        Query query = new Query();
        // query.addCriteria(Criteria.where("userName").is("shaurya"));

        // query.addCriteria(Criteria.where("email").exists(true));
        // query.addCriteria(Criteria.where("email").ne(null).ne(""));

        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        //the above query is of and operator


        //this is how we use OR operator
        // Criteria criteria = new Criteria();
        // query.addCriteria(criteria.orOperator(Criteria.where("email").exists(true), Criteria.where("sentimentAnalysis").is(true)));


        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
