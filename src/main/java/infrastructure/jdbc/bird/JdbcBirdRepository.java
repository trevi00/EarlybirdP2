// infrastructure.jdbc.bird.JdbcBirdRepository.java
package infrastructure.jdbc.bird;

import domain.bird.model.Bird;
import domain.bird.model.BirdStage;
import domain.bird.repository.BirdRepository;

import java.sql.*;
import java.time.LocalDate;

public class JdbcBirdRepository implements BirdRepository {

    private final Connection conn;

    public JdbcBirdRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Bird findByUserId(String userId) {
        String sql = "SELECT * FROM BIRDS WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return map(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 기본 새가 없다면 처음 생성 (stage = EGG)
        Bird newBird = new Bird(
                generateId(userId),
                userId,
                BirdStage.EGG,
                0,
                LocalDate.now()
        );
        save(newBird);
        return newBird;
    }

    @Override
    public void save(Bird bird) {
        String sql = """
            MERGE INTO BIRDS b
            USING (SELECT ? AS id FROM dual) s
            ON (b.id = s.id)
            WHEN MATCHED THEN
              UPDATE SET stage = ?, point = ?, born_date = ?, user_id = ?
            WHEN NOT MATCHED THEN
              INSERT (id, user_id, stage, point, born_date)
              VALUES (?, ?, ?, ?, ?)
            """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // for MATCHED
            pstmt.setString(1, bird.getId());
            pstmt.setString(2, bird.getStage().name());
            pstmt.setInt(3, bird.getPoint());
            pstmt.setDate(4, Date.valueOf(bird.getBornDate()));
            pstmt.setString(5, bird.getUserId());

            // for NOT MATCHED
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

    private Bird map(ResultSet rs) throws SQLException {
        return new Bird(
                rs.getString("id"),
                rs.getString("user_id"),
                BirdStage.valueOf(rs.getString("stage")),
                rs.getInt("point"),
                rs.getDate("born_date").toLocalDate()
        );
    }

    private String generateId(String userId) {
        return "bird-" + userId.replaceAll("[^a-zA-Z0-9]", "");
    }
}
