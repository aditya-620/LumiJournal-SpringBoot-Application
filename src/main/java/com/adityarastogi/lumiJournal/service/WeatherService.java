package com.adityarastogi.lumiJournal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.adityarastogi.lumiJournal.apiResponse.WeatherResponse;
import com.adityarastogi.lumiJournal.cache.AppCache;
import com.adityarastogi.lumiJournal.constants.PlaceHolders;


@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private AppCache appCache; // It is a class which is used to cache the data in memory for faster access
    
    @Autowired
    private RestTemplate restTemplate; // It is a class in a spring framework, which process HTTPS request and give response

    public WeatherResponse getWeather(String city){
        String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolders.CITY, city).replace(PlaceHolders.API_KEY, apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();  
        return body;
    }
}
