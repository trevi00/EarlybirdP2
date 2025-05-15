package domain.weather.repository;

import domain.weather.model.Weather;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class InMemoryWeatherCacheRepository implements WeatherCacheRepository {

    private final Map<String, Weather> cache = new HashMap<>();
    private LocalDate cacheDate = LocalDate.now();

    @Override
    public Weather getToday(String cityName) {
        if (!isToday()) {
            clear();
        }
        return cache.get(cityName);
    }

    @Override
    public void saveToday(String cityName, Weather weather) {
        if (!isToday()) {
            clear();
        }
        cache.put(cityName, weather);
    }

    private boolean isToday() {
        return LocalDate.now().equals(cacheDate);
    }

    private void clear() {
        cache.clear();
        cacheDate = LocalDate.now();
    }
}
