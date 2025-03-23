


package com.example.JournalApplication.service;


import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class WeatherService {

    private final String API_KEY = "88512b742da633e048b6f52ffb7960b2"; // Tumhari API Key
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public String getWeatherByCity(String city) {
        String url = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric"; // Metric units (°C)

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            // Convert Response to JSON Object
            JSONObject jsonResponse = new JSONObject(response.getBody());

            // Extract Useful Data
            String cityName = jsonResponse.getString("name");
            double temperature = jsonResponse.getJSONObject("main").getDouble("temp");
            String weatherDescription = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description");

            // Format Response
            return "City: " + cityName + "\nTemperature: " + temperature + "°C\nWeather: " + weatherDescription;

        } catch (Exception e) {
            return "Error fetching weather data. Please check city name or try again later!";
        }
    }
}
