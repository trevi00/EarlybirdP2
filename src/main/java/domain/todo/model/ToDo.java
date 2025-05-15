// domain.todo.model.ToDo.java
package domain.todo.model;

import java.time.LocalDate;

public class ToDo {
    private final String id;
    private final String userId;
    private final LocalDate date;
    private final String title;
    private final String content;
    private final boolean done;

    public ToDo(String id, String userId, LocalDate date, String title, String content, boolean done) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.title = title;
        this.content = content;
        this.done = done;
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public LocalDate getDate() { return date; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public boolean isDone() { return done; }

    public ToDo markAsDone() {
        return new ToDo(id, userId, date, title, content, true);
    }
}
