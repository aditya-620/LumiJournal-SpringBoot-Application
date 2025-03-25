package com.adityarastogi.lumiJournal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LumiJournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(LumiJournalApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}

	//MongoDatabaseFactory is an interface that provides access to a MongoDB database.
	//it is used to create connections to the database

	//PlatformTransactionManager - an interface
	//iska impelementation - new MongoTransactionManager(); yeh hai, taki transaction ko handle kar paye yeh
	
}
