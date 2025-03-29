package com.adityarastogi.lumiJournal.scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.adityarastogi.lumiJournal.cache.AppCache;
import com.adityarastogi.lumiJournal.entity.JournalEntry;
import com.adityarastogi.lumiJournal.entity.User;
import com.adityarastogi.lumiJournal.enums.Sentiment;
import com.adityarastogi.lumiJournal.repository.UserRepositoryImpl;
import com.adityarastogi.lumiJournal.service.EmailService;


@Component
public class UserScheduler {

    @Autowired
    private AppCache appCache;
    
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    // Send email to users every sunday at 9 AM
    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUserAndSendSaMail(){
        List<User> users = userRepositoryImpl.getUserForSA();

        for(User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());

            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();

            // String entry = String.join(" ", filteredList);
            // String sentiment = sentimentAnalysisService.getSentiment(entry);
            // emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days: ", sentiment);

            for (Sentiment sentiment : sentiments) {
                if (sentiment != null)
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            if(mostFrequentSentiment!=null){
                emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days: ", mostFrequentSentiment.toString());
            }


        }
    }

    // In memory cache refresh every sunday
    @Scheduled(cron = "0 0 9 * * SUN")
    public void clearAppCache(){
        appCache.init();
    }
    
}
