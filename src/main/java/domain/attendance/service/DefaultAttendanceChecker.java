// domain.attendance.service.DefaultAttendanceChecker.java
package domain.attendance.service;

import domain.attendance.repository.AttendanceRepository;
import domain.attendance.model.Attendance;
import common.id.IdGenerator;

import java.time.LocalDate;

public class DefaultAttendanceChecker implements AttendanceChecker {

    private final AttendanceRepository repository;

    public DefaultAttendanceChecker(AttendanceRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean check(String userId, LocalDate date) {
        if (repository.exists(userId, date)) return false;
        String id = IdGenerator.generate("att", userId, date.toString());
        repository.save(id, userId, date);
        return true;
    }
}
