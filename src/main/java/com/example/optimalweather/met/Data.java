
package com.example.optimalweather.met;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "instant",
    "next_12_hours",
    "next_1_hours",
    "next_6_hours"
})
public class Data {

    @JsonProperty("instant")
    private com.example.optimalweather.met.Instant instant;
    @JsonProperty("next_12_hours")
    private Next12Hours next12Hours;
    @JsonProperty("next_1_hours")
    private Next1Hours next1Hours;
    @JsonProperty("next_6_hours")
    private Next6Hours next6Hours;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("instant")
    public com.example.optimalweather.met.Instant getInstant() {
        return instant;
    }

    @JsonProperty("instant")
    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    @JsonProperty("next_12_hours")
    public Next12Hours getNext12Hours() {
        return next12Hours;
    }

    @JsonProperty("next_12_hours")
    public void setNext12Hours(Next12Hours next12Hours) {
        this.next12Hours = next12Hours;
    }

    @JsonProperty("next_1_hours")
    public Next1Hours getNext1Hours() {
        return next1Hours;
    }

    @JsonProperty("next_1_hours")
    public void setNext1Hours(Next1Hours next1Hours) {
        this.next1Hours = next1Hours;
    }

    @JsonProperty("next_6_hours")
    public Next6Hours getNext6Hours() {
        return next6Hours;
    }

    @JsonProperty("next_6_hours")
    public void setNext6Hours(Next6Hours next6Hours) {
        this.next6Hours = next6Hours;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Data.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("instant");
        sb.append('=');
        sb.append(((this.instant == null)?"<null>":this.instant));
        sb.append(',');
        sb.append("next12Hours");
        sb.append('=');
        sb.append(((this.next12Hours == null)?"<null>":this.next12Hours));
        sb.append(',');
        sb.append("next1Hours");
        sb.append('=');
        sb.append(((this.next1Hours == null)?"<null>":this.next1Hours));
        sb.append(',');
        sb.append("next6Hours");
        sb.append('=');
        sb.append(((this.next6Hours == null)?"<null>":this.next6Hours));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
