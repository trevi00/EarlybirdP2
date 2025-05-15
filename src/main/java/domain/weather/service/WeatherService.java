// domain.weather.service.WeatherService.java
package domain.weather.service;

import domain.weather.model.Weather;

public interface WeatherService {
    Weather getTodayWeather(String cityName);
}
