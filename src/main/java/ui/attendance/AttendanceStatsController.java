// domain.attendance.controller.AttendanceStatsController.java
package domain.attendance.controller;

import domain.attendance.service.AttendanceStatsService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Set;

public class AttendanceStatsController {

    private final AttendanceStatsService service;

    public AttendanceStatsController(AttendanceStatsService service) {
        this.service = service;
    }

    public Set<LocalDate> getAttendanceDays(String userId, YearMonth month) {
        return service.getAttendanceDaysInMonth(userId, month);
    }

    public int getCount(String userId, YearMonth month) {
        return service.getAttendanceCountInMonth(userId, month);
    }
}
