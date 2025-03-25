package com.example.JournalApplication.service;
import com.example.JournalApplication.config.WeatherConfig;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private final WeatherConfig weatherConfig;

    // Constructor Injection
    public WeatherService(WeatherConfig weatherConfig) {
        this.weatherConfig = weatherConfig;
    }

    public String getWeatherByCity(String city) {
        String url = weatherConfig.getUrl() + "?q=" + city + "&appid=" + weatherConfig.getKey() + "&units=metric";

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            JSONObject jsonResponse = new JSONObject(response.getBody());

            String cityName = jsonResponse.getString("name");
            double temperature = jsonResponse.getJSONObject("main").getDouble("temp");
            String weatherDescription = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description");

            return "City: " + cityName + "\nTemperature: " + temperature + "°C\nWeather: " + weatherDescription;

        } catch (Exception e) {
            return "Error fetching weather data. Please check city name or try again later!";
        }
    }
}
