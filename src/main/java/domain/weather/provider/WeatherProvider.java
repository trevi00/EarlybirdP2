package domain.weather.provider;

import domain.weather.model.Weather;

public interface WeatherProvider {
    Weather fetchToday(String cityName);
}
