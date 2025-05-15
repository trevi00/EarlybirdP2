// domain.attendance.service.AttendanceChecker.java
package domain.attendance.service;

import java.time.LocalDate;

public interface AttendanceChecker {
    boolean check(String userId, LocalDate date);
}
