// infrastructure.cache.weather.WeatherCacheManager.java
package infrastructure.cache.weather;

import domain.weather.model.Weather;

public interface WeatherCacheManager {
    Weather getTodayWeather(String cityName);
    void saveTodayWeather(String cityName, Weather weather);
}
