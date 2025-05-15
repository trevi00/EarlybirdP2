package domain.weather.service;

import domain.weather.model.Weather;
import domain.weather.repository.WeatherCacheRepository;
import domain.weather.provider.WeatherProvider;

public class DefaultWeatherService implements WeatherService {

    private final WeatherCacheRepository cacheRepository;
    private final WeatherProvider weatherProvider;

    public DefaultWeatherService(WeatherCacheRepository cacheRepository, WeatherProvider weatherProvider) {
        this.cacheRepository = cacheRepository;
        this.weatherProvider = weatherProvider;
    }

    @Override
    public Weather getToday(String cityName) {
        Weather cached = cacheRepository.getToday(cityName);
        if (cached != null) return cached;

        Weather fetched = weatherProvider.fetchToday(cityName);
        if (fetched != null) {
            cacheRepository.saveToday(cityName, fetched);
        }

        return fetched;
    }
}
