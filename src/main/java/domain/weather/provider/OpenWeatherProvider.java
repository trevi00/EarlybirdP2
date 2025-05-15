package domain.weather.provider;

import domain.weather.model.Weather;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenWeatherProvider implements WeatherProvider {

    private static final String API_KEY = "8ee01c131076cf0b6d2f00fdd1ff9836"; // 교체 필요
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    @Override
    public Weather fetchToday(String cityName) {
        try {
            String urlStr = API_URL + "?q=" + cityName + "&appid=" + API_KEY + "&units=metric&lang=kr";
            HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                System.out.println("API 응답 오류: " + conn.getResponseCode());
                return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            reader.lines().forEach(sb::append);
            JSONObject json = new JSONObject(sb.toString());

            String name = json.getString("name");
            double temp = json.getJSONObject("main").getDouble("temp");
            String desc = json.getJSONArray("weather").getJSONObject(0).getString("description");

            return new Weather(name, temp, desc);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
