package com.example.optimalweather.data;

import com.example.optimalweather.met.Instant;
import com.example.optimalweather.met.Met;
import com.example.optimalweather.met.Timeseries;
import com.example.optimalweather.meteo.Hourly;
import com.example.optimalweather.meteo.Meteo;
import com.example.optimalweather.smhi.Parameter;
import com.example.optimalweather.smhi.Smhi;
import com.example.optimalweather.smhi.TimeSeries;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class WeatherRetriever {

    public WeatherData getSmhiData() {
        WebClient client = WebClient.create();

        Mono<Smhi> resultSmhi = client
                .get()
                .uri("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json")
                .retrieve()
                .bodyToMono(Smhi.class);

        Smhi smhiData = resultSmhi.block();
        if (smhiData != null && smhiData.getTimeSeries() != null) {
            TimeSeries timeSeries = smhiData.getTimeSeries().get(26);

            Double temperature = null;
            Double windSpeed = null;
            String source = null;

            for (Parameter parameter : timeSeries.getParameters()) {
                if ("t".equals(parameter.getName())) {
                    temperature = parameter.getValues().get(0);
                } else if ("ws".equals(parameter.getName())) {
                    windSpeed = parameter.getValues().get(0);
                }
            }
            WeatherData weatherData = new WeatherData(temperature, windSpeed, source);
            System.out.println("SMHI t: " + weatherData.getTemperature() + ", ws: " + weatherData.getWindSpeed());
            return weatherData;
        }
        return null;
    }

    public WeatherData getMeteoData() {
        WebClient client = WebClient.create();

        Mono<Meteo> resultMeteo = client
                .get()
                .uri("https://api.open-meteo.com/v1/forecast?latitude=59.3094&longitude=18.0234&hourly=temperature_2m,wind_speed_10m&wind_speed_unit=ms")
                .retrieve()
                .bodyToMono(Meteo.class);

        Meteo meteoData = resultMeteo.block();
        if (meteoData != null && meteoData.getHourly() != null) {
            Hourly hourly = meteoData.getHourly();
            List<String> timeList = hourly.getTime();
            List<Double> temperatureList = hourly.getTemperature2m();
            List<Double> windSpeedList = hourly.getWindSpeed10m();

            Double temperature;
            Double windSpeed;
            String source;

            if (timeList != null && temperatureList != null && windSpeedList != null) {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime targetTime = now.plusHours(24);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

                for (int i = 0; i < timeList.size(); i++) {
                    LocalDateTime tsTime = LocalDateTime.parse(timeList.get(i), formatter);
                    if (tsTime.isAfter(targetTime) || tsTime.equals(targetTime)) {
                        temperature = temperatureList.get(i);
                        windSpeed = windSpeedList.get(i);
                        source = "Meteo";
                        WeatherData weatherData = new WeatherData(temperature, windSpeed, source);
                        System.out.println("Meteo t: " + weatherData.getTemperature() + ", ws: " + weatherData.getWindSpeed());
                        return weatherData;
                    }
                }
            }
        }
        return null;
    }

    public WeatherData getMetData() {
        WebClient client = WebClient.create();

        Mono<Met> resultMet = client
                .get()
                .uri("https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=59.3110&lon=18.0300")
                .retrieve()
                .bodyToMono(Met.class);

        Met metData24hrs = resultMet.block();
        if (metData24hrs != null && metData24hrs.getProperties() != null) {
            List<Timeseries> timeseriesList = metData24hrs.getProperties().getTimeseries();
            if (timeseriesList != null && !timeseriesList.isEmpty()) {
                ZonedDateTime now = ZonedDateTime.now();
                ZonedDateTime targetTime = now.plus(24, ChronoUnit.HOURS);
                for (Timeseries ts : timeseriesList) {
                    ZonedDateTime tsTime = ZonedDateTime.parse(ts.getTime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                    if (tsTime.isAfter(targetTime) || tsTime.isEqual(targetTime)) {
                        Instant instant = ts.getData().getInstant();
                        if (instant != null && instant.getDetails() != null) {
                            Double temperature = instant.getDetails().getAirTemperature();
                            Double windSpeed = instant.getDetails().getWindSpeed();
                            String source = "MET";
                            WeatherData weatherData = new WeatherData(temperature, windSpeed, source);
                            System.out.println("MET t: " + weatherData.getTemperature() + ", ws: " + weatherData.getWindSpeed());
                            return weatherData;
                        }
                    }
                }
            }
        }
        return null;
    }
}
