package com.example.optimalweather.service;

import com.example.optimalweather.data.WeatherData;
import com.example.optimalweather.data.WeatherRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Autowired
    WeatherRetriever weatherRetriever;

    public WeatherData dreamConditions() {

        WeatherData smhiData = weatherRetriever.getSmhiData();
        WeatherData metData = weatherRetriever.getMetData();
        WeatherData meteoData = weatherRetriever.getMeteoData();

        // Chosen options for definition of "best" weather:
        double targetTemperature = 21.9;
        double targetWindSpeed = 2.7;

        if (smhiData != null && metData != null && meteoData != null) {
            double smhiTempDiff = Math.abs(smhiData.getTemperature() - targetTemperature);
            double smhiWsDiff = Math.abs(smhiData.getWindSpeed() - targetWindSpeed);
            double smhiScore = smhiTempDiff + smhiWsDiff;

            double metTempDiff = Math.abs(metData.getTemperature() - targetTemperature);
            double metWsDiff = Math.abs(metData.getWindSpeed() - targetWindSpeed);
            double metScore = metTempDiff + metWsDiff;

            double meteoTempDiff = Math.abs(meteoData.getTemperature() - targetTemperature);
            double meteoWsDiff = Math.abs(meteoData.getTemperature() - targetWindSpeed);
            double meteoScore = meteoTempDiff + meteoWsDiff;

            if (smhiScore <= metScore && smhiScore <= meteoScore) {
                return new WeatherData(smhiData.getTemperature(), smhiData.getWindSpeed(), "SMHI");
            } else if (metScore <= smhiScore && metScore <= meteoScore) {
                return new WeatherData(metData.getTemperature(), metData.getWindSpeed(), "MET");
            } else {
                return new WeatherData(meteoData.getTemperature(), meteoData.getWindSpeed(), "Meteo");
            }
        } else {
            System.out.println("Unable to retrieve");
        }

        return null;
    }
}
