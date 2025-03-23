
package com.example.JournalApplication.controller;


import com.example.JournalApplication.service.WeatherService;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public String getWeather(@RequestParam String city) {
        return weatherService.getWeatherByCity(city);
    }
}
