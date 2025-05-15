package domain.diary.model;

import java.time.LocalDate;

public class Diary {

    private final String userId;
    private final LocalDate date;
    private final String weather;
    private final String title;
    private final String content;

    public Diary(String userId, LocalDate date, String weather, String title, String content) {
        this.userId = userId;
        this.date = date;
        this.weather = weather;
        this.title = title;
        this.content = content;
    }

    public String getUserId() { return userId; }
    public LocalDate getDate() { return date; }
    public String getWeather() { return weather; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
}
