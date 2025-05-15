package domain.attendance.service;

import domain.attendance.repository.AttendanceRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

public class AttendanceStatsService {

    private final AttendanceRepository repository;

    public AttendanceStatsService(AttendanceRepository repository) {
        this.repository = repository;
    }

    public Set<LocalDate> getAttendanceDaysInMonth(String userId, YearMonth month) {
        Set<LocalDate> result = new HashSet<>();

        for (int day = 1; day <= month.lengthOfMonth(); day++) {
            LocalDate date = month.atDay(day);
            if (repository.existsByDate(userId, date)) {
                result.add(date);
            }
        }

        return result;
    }

    public int getAttendanceCountInMonth(String userId, YearMonth month) {
        return getAttendanceDaysInMonth(userId, month).size();
    }
}
