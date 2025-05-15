// infrastructure.cache.weather.InMemoryWeatherCacheManager.java
package infrastructure.cache.weather;

import domain.weather.model.Weather;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class InMemoryWeatherCacheManager implements WeatherCacheManager {

    private Map<String, Weather> cache = new HashMap<>();
    private LocalDate cachedDate = LocalDate.now();

    @Override
    public Weather getTodayWeather(String cityName) {
        if (!isToday()) clearCache();
        return cache.get(cityName);
    }

    @Override
    public void saveTodayWeather(String cityName, Weather weather) {
        if (!isToday()) clearCache();
        cache.put(cityName, weather);
    }

    private boolean isToday() {
        return LocalDate.now().equals(cachedDate);
    }

    private void clearCache() {
        cache.clear();
        cachedDate = LocalDate.now();
    }
}
