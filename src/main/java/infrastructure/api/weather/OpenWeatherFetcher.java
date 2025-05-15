// infrastructure.api.weather.OpenWeatherFetcher.java
package infrastructure.api.weather;

import domain.weather.model.Weather;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenWeatherFetcher implements WeatherFetcher {

    private static final String API_KEY = "8ee01c131076cf0b6d2f00fdd1ff9836";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    @Override
    public Weather fetchTodayWeather(String cityName) {
        try {
            String urlStr = API_URL + "?q=" + cityName + "&appid=" + API_KEY + "&units=metric&lang=kr";
            HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                System.out.println("❌ OpenWeather API 호출 실패");
                return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder jsonStr = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) jsonStr.append(line);

            JSONObject json = new JSONObject(jsonStr.toString());

            return new Weather(
                    json.getString("name"),
                    json.getJSONObject("main").getDouble("temp"),
                    json.getJSONArray("weather").getJSONObject(0).getString("description")
            );

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
