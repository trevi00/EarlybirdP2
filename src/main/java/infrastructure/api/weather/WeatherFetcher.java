// infrastructure.api.weather.WeatherFetcher.java
package infrastructure.api.weather;

import domain.weather.model.Weather;

public interface WeatherFetcher {
    Weather fetchTodayWeather(String cityName);
}
