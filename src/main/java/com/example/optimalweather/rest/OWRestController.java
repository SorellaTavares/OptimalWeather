package com.example.optimalweather.rest;

import com.example.optimalweather.data.WeatherData;
import com.example.optimalweather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OWRestController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("/rs/optimalweather")
    public WeatherData getOptimalWeather() {
        return weatherService.dreamConditions();
    }
}
