// domain.weather.controller.WeatherController.java
package domain.weather.controller;

import domain.weather.model.Weather;
import domain.weather.service.WeatherService;

public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public Weather getTodayWeather(String cityName) {
        return weatherService.getTodayWeather(cityName);
    }
}
