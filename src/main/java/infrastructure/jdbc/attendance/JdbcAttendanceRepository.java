// infrastructure.jdbc.attendance.JdbcAttendanceRepository.java
package infrastructure.jdbc.attendance;

import domain.attendance.repository.AttendanceRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class JdbcAttendanceRepository implements AttendanceRepository {

    private final Connection conn;

    public JdbcAttendanceRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean exists(String userId, LocalDate date) {
        String sql = "SELECT COUNT(*) FROM ATTENDANCE WHERE user_id = ? AND attend_date = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setDate(2, Date.valueOf(date));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void save(String id, String userId, LocalDate date) {
        String sql = "INSERT INTO ATTENDANCE (id, user_id, attend_date) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, userId);
            pstmt.setDate(3, Date.valueOf(date));
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
    public Set<LocalDate> findAttendanceDaysInMonth(String userId, int year, int month) {
        Set<LocalDate> result = new HashSet<>();
        String sql = """
            SELECT attend_date 
            FROM ATTENDANCE 
            WHERE user_id = ? 
              AND EXTRACT(YEAR FROM attend_date) = ? 
              AND EXTRACT(MONTH FROM attend_date) = ?
        """;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setInt(2, year);
            pstmt.setInt(3, month);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(rs.getDate("attend_date").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
