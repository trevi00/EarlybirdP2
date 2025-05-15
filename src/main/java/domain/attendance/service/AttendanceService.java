package domain.attendance.service;

import java.time.LocalDate;

public interface AttendanceService {

    boolean checkAttendance(String userId, LocalDate date);
}
