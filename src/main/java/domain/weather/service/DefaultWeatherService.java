// domain.weather.service.DefaultWeatherService.java
package domain.weather.service;

import domain.weather.model.Weather;
import infrastructure.cache.weather.WeatherCacheManager;
import infrastructure.api.weather.WeatherFetcher;

public class DefaultWeatherService implements WeatherService {

    private final WeatherCacheManager cacheManager;
    private final WeatherFetcher fetcher;

    public DefaultWeatherService(WeatherCacheManager cacheManager, WeatherFetcher fetcher) {
        this.cacheManager = cacheManager;
        this.fetcher = fetcher;
    }

    @Override
    public Weather getTodayWeather(String cityName) {
        Weather cached = cacheManager.getTodayWeather(cityName);
        if (cached != null) return cached;

        Weather fetched = fetcher.fetchTodayWeather(cityName);
        if (fetched != null) cacheManager.saveTodayWeather(cityName, fetched);

        return fetched;
    }
}
