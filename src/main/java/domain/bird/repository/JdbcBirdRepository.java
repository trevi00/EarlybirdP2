package domain.bird.repository;

import domain.bird.model.Bird;
import domain.bird.model.BirdStage;

import java.sql.*;
import java.time.LocalDate;

public class JdbcBirdRepository implements BirdRepository {

    private final Connection conn;

    public JdbcBirdRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(Bird bird) {
        String sql = """
            MERGE INTO BIRDS b
            USING (SELECT ? AS id FROM dual) s
            ON (b.id = s.id)
            WHEN MATCHED THEN
              UPDATE SET user_id = ?, stage = ?, point = ?, born_date = ?
            WHEN NOT MATCHED THEN
              INSERT (id, user_id, stage, point, born_date)
              VALUES (?, ?, ?, ?, ?)
        """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bird.getId());
            pstmt.setString(2, bird.getUserId());
            pstmt.setString(3, bird.getStage().name());
            pstmt.setInt(4, bird.getPoint());
            pstmt.setDate(5, Date.valueOf(bird.getBornDate()));

            pstmt.setString(6, bird.getId());
            pstmt.setString(7, bird.getUserId());
            pstmt.setString(8, bird.getStage().name());
            pstmt.setInt(9, bird.getPoint());
            pstmt.setDate(10, Date.valueOf(bird.getBornDate()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bird findByUsername(String userId) {
        String sql = "SELECT * FROM BIRDS WHERE user_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Bird(
                        rs.getString("id"),
                        rs.getString("user_id"),
                        BirdStage.valueOf(rs.getString("stage")),
                        rs.getInt("point"),
                        rs.getDate("born_date").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
