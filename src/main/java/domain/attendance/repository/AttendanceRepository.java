// domain.attendance.repository.AttendanceRepository.java
package domain.attendance.repository;

import java.time.LocalDate;
import java.util.Set;

public interface AttendanceRepository {
    boolean exists(String userId, LocalDate date);
    void save(String id, String userId, LocalDate date);
    Set<LocalDate> findAttendanceDaysInMonth(String userId, int year, int month);
}
