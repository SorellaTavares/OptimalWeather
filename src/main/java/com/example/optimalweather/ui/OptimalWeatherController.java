package com.example.optimalweather.ui;


import com.example.optimalweather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OptimalWeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("/optimalweather")
    public String getDreamConditions(Model m) {
        String conditions = weatherService.dreamConditions().toString();
        m.addAttribute("conditions", conditions);
        return "optimalweather";
    }
}
