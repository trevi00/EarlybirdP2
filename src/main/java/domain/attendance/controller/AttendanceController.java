// domain.attendance.controller.AttendanceController.java
package domain.attendance.controller;

import domain.attendance.service.AttendanceChecker;
import domain.attendance.service.AttendanceStatsService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Set;

public class AttendanceController {

    private final AttendanceChecker checker;
    private final AttendanceStatsService stats;

    public AttendanceController(AttendanceChecker checker, AttendanceStatsService stats) {
        this.checker = checker;
        this.stats = stats;
    }

    public boolean checkAttendance(String userId, LocalDate date) {
        return checker.check(userId, date);
    }

    public Set<LocalDate> getMonthlyAttendance(String userId, YearMonth month) {
        return stats.getAttendanceDays(userId, month);
    }

    public int getMonthlyAttendanceCount(String userId, YearMonth month) {
        return stats.getAttendanceCount(userId, month);
    }
}
