// domain.weather.model.Weather.java
package domain.weather.model;

public class Weather {
    private final String cityName;
    private final double temperature;
    private final String description;

    public Weather(String cityName, double temperature, String description) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.description = description;
    }

    public String getCityName() {
        return cityName;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }
}
