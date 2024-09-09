
package com.example.optimalweather.meteo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "time",
    "temperature_2m",
    "wind_speed_10m"
})

public class Hourly {

    @JsonProperty("time")
    private List<String> time;
    @JsonProperty("temperature_2m")
    private List<Double> temperature2m;
    @JsonProperty("wind_speed_10m")
    private List<Double> windSpeed10m;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("time")
    public List<String> getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(List<String> time) {
        this.time = time;
    }

    @JsonProperty("temperature_2m")
    public List<Double> getTemperature2m() {
        return temperature2m;
    }

    @JsonProperty("temperature_2m")
    public void setTemperature2m(List<Double> temperature2m) {
        this.temperature2m = temperature2m;
    }

    @JsonProperty("wind_speed_10m")
    public List<Double> getWindSpeed10m() {
        return windSpeed10m;
    }

    @JsonProperty("wind_speed_10m")
    public void setWindSpeed10m(List<Double> windSpeed10m) {
        this.windSpeed10m = windSpeed10m;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
