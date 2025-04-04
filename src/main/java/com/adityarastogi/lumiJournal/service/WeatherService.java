package com.adityarastogi.lumiJournal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.adityarastogi.lumiJournal.apiResponse.WeatherResponse;
import com.adityarastogi.lumiJournal.cache.AppCache;


@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private AppCache appCache; // It is a class which is used to cache the data in memory for faster access
    
    @Autowired
    private RestTemplate restTemplate; // It is a class in a spring framework, which process HTTPS request and give response

    @Autowired
    private RedisService redisService;

    // public WeatherResponse getWeather(String city){
    //     String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace("<city>", city).replace("<apiKey>", apiKey);
    //     ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
    //     WeatherResponse body = response.getBody();  
    //     return body;
    // }

    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse != null){
            return weatherResponse;
        }

        else{
            String finalAPI = appCache.appCache.get("weather_api").replace("<city>", city).replace("<apiKey>", apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body!=null){
                redisService.set("weather_of_" + city, body, 300L);
            }
            return body;
        } 
    }
}
