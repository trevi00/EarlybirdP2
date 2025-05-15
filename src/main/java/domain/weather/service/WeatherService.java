package domain.weather.service;

import domain.weather.model.Weather;

public interface WeatherService {
    Weather getToday(String cityName);
}
