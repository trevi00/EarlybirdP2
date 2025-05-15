package domain.attendance.model;

import java.time.LocalDate;

/**
 * 출석 기록 도메인 모델
 */
public class Attendance {

    private final String id;
    private final String userId;
    private final LocalDate date;

    public Attendance(String id, String userId, LocalDate date) {
        this.id = id;
        this.userId = userId;
        this.date = date;
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public LocalDate getDate() { return date; }
}
