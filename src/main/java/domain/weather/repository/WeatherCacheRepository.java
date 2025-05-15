package domain.weather.repository;

import domain.weather.model.Weather;

public interface WeatherCacheRepository {
    Weather getToday(String cityName);
    void saveToday(String cityName, Weather weather);
}
