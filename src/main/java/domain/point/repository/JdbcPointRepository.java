package domain.point.repository;

import java.sql.*;

public class JdbcPointRepository implements PointRepository {

    private final Connection conn;

    public JdbcPointRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int getCurrentPoint(String userId) {
        String sql = "SELECT amount FROM POINTS WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt("amount");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void addPoint(String userId, int amount) {
        String sql = """
            MERGE INTO POINTS p
            USING (SELECT ? AS user_id FROM dual) s
            ON (p.user_id = s.user_id)
            WHEN MATCHED THEN
              UPDATE SET amount = amount + ?
            WHEN NOT MATCHED THEN
              INSERT (user_id, amount) VALUES (?, ?)
        """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setInt(2, amount);
            pstmt.setString(3, userId);
            pstmt.setInt(4, amount);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subtractPoint(String userId, int amount) {
        String sql = "UPDATE POINTS SET amount = amount - ? WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, amount);
            pstmt.setString(2, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
