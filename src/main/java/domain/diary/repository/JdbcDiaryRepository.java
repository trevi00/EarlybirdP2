package domain.diary.repository;

import domain.diary.model.Diary;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcDiaryRepository implements DiaryRepository {

    private final Connection conn;

    public JdbcDiaryRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(Diary diary) {
        String sql = """
            MERGE INTO DIARY d
            USING (SELECT ? AS user_id, ? AS diary_date FROM dual) s
            ON (d.user_id = s.user_id AND d.diary_date = s.diary_date)
            WHEN MATCHED THEN
              UPDATE SET weather = ?, title = ?, content = ?
            WHEN NOT MATCHED THEN
              INSERT (user_id, diary_date, weather, title, content)
              VALUES (?, ?, ?, ?, ?)
        """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, diary.getUserId());
            pstmt.setDate(2, Date.valueOf(diary.getDate()));
            pstmt.setString(3, diary.getWeather());
            pstmt.setString(4, diary.getTitle());
            pstmt.setString(5, diary.getContent());
            pstmt.setString(6, diary.getUserId());
            pstmt.setDate(7, Date.valueOf(diary.getDate()));
            pstmt.setString(8, diary.getWeather());
            pstmt.setString(9, diary.getTitle());
            pstmt.setString(10, diary.getContent());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Diary> findAllByUserId(String userId) {
        List<Diary> result = new ArrayList<>();
        String sql = "SELECT * FROM DIARY WHERE user_id = ? ORDER BY diary_date DESC";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                result.add(new Diary(
                        rs.getString("user_id"),
                        rs.getDate("diary_date").toLocalDate(),
                        rs.getString("weather"),
                        rs.getString("title"),
                        rs.getString("content")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Diary findByUserIdAndDate(String userId, LocalDate date) {
        String sql = "SELECT * FROM DIARY WHERE user_id = ? AND diary_date = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setDate(2, Date.valueOf(date));
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Diary(
                        rs.getString("user_id"),
                        rs.getDate("diary_date").toLocalDate(),
                        rs.getString("weather"),
                        rs.getString("title"),
                        rs.getString("content")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void deleteByUserIdAndDate(String userId, LocalDate date) {
        String sql = "DELETE FROM DIARY WHERE user_id = ? AND diary_date = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setDate(2, Date.valueOf(date));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
