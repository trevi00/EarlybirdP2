package domain.attendance.repository;

import java.time.LocalDate;

public interface AttendanceRepository {

    boolean existsByDate(String userId, LocalDate date);

    void save(String userId, LocalDate date);

    boolean hasAnyAttendance(String userId);
}
