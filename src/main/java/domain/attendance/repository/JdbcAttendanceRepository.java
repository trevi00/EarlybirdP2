package domain.attendance.repository;

import domain.attendance.model.Attendance;

import java.sql.*;
import java.time.LocalDate;

public class JdbcAttendanceRepository implements AttendanceRepository {

    private final Connection conn;

    public JdbcAttendanceRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean existsByDate(String username, LocalDate date) {
        String sql = "SELECT COUNT(*) FROM ATTENDANCE WHERE username = ? AND attend_date = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setDate(2, Date.valueOf(date));
            ResultSet rs = pstmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void save(String username, LocalDate date) {
        String sql = "INSERT INTO ATTENDANCE (username, attend_date) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setDate(2, Date.valueOf(date));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().contains("ORA-00001")) {
                System.out.println("❗ 이미 출석한 날짜입니다.");
            } else {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean hasAnyAttendance(String username) {
        String sql = "SELECT 1 FROM ATTENDANCE WHERE username = ? FETCH FIRST 1 ROWS ONLY";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
