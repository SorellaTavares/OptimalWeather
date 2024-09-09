package com.example.optimalweather.data;

public class WeatherData {

    private Double temperature;
    private Double windSpeed;
    private String source;

    public WeatherData(Double temperature, Double windSpeed, String source) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.source = source;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public String getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "Temperature: " + temperature + ", wind speed: " + windSpeed + " (Source: " + source + ")";
    }

}
