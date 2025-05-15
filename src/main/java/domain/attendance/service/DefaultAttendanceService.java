package domain.attendance.service;

import domain.attendance.repository.AttendanceRepository;
import domain.point.service.PointService;

import java.time.LocalDate;

public class DefaultAttendanceService implements AttendanceService {

    private final AttendanceRepository repository;
    private final PointService pointService;

    public DefaultAttendanceService(AttendanceRepository repository, PointService pointService) {
        this.repository = repository;
        this.pointService = pointService;
    }

    @Override
    public boolean checkAttendance(String userId, LocalDate date) {
        if (repository.existsByDate(userId, date)) {
            return false;
        }

        repository.save(userId, date);
        pointService.addPoint(userId, 10); // 출석 시 포인트 지급
        return true;
    }

    public AttendanceRepository getRepository() {
        return repository;
    }
}
