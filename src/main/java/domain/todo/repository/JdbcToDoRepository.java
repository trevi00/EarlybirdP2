package domain.todo.repository;

import domain.todo.model.ToDo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcToDoRepository implements ToDoRepository {

    private final Connection conn;

    public JdbcToDoRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(ToDo todo) {
        String sql = """
            MERGE INTO TODOS t
            USING (SELECT ? AS user_id, ? AS todo_date FROM dual) s
            ON (t.user_id = s.user_id AND t.todo_date = s.todo_date)
            WHEN MATCHED THEN
              UPDATE SET title = ?, content = ?, done = ?
            WHEN NOT MATCHED THEN
              INSERT (user_id, todo_date, title, content, done)
              VALUES (?, ?, ?, ?, ?)
        """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, todo.getUserId());
            pstmt.setDate(2, Date.valueOf(todo.getDate()));
            pstmt.setString(3, todo.getTitle());
            pstmt.setString(4, todo.getContent());
            pstmt.setString(5, todo.isDone() ? "Y" : "N");
            pstmt.setString(6, todo.getUserId());
            pstmt.setDate(7, Date.valueOf(todo.getDate()));
            pstmt.setString(8, todo.getTitle());
            pstmt.setString(9, todo.getContent());
            pstmt.setString(10, todo.isDone() ? "Y" : "N");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean exists(String userId, LocalDate date) {
        String sql = "SELECT COUNT(*) FROM TODOS WHERE user_id = ? AND todo_date = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setDate(2, Date.valueOf(date));
            ResultSet rs = pstmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ToDo findByDate(String userId, LocalDate date) {
        String sql = "SELECT * FROM TODOS WHERE user_id = ? AND todo_date = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setDate(2, Date.valueOf(date));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new ToDo(
                        rs.getString("user_id"),
                        rs.getDate("todo_date").toLocalDate(),
                        rs.getString("title"),
                        rs.getString("content"),
                        "Y".equalsIgnoreCase(rs.getString("done"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ToDo> findAll(String userId) {
        List<ToDo> list = new ArrayList<>();
        String sql = "SELECT * FROM TODOS WHERE user_id = ? ORDER BY todo_date DESC";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new ToDo(
                        rs.getString("user_id"),
                        rs.getDate("todo_date").toLocalDate(),
                        rs.getString("title"),
                        rs.getString("content"),
                        "Y".equalsIgnoreCase(rs.getString("done"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void delete(String userId, LocalDate date) {
        String sql = "DELETE FROM TODOS WHERE user_id = ? AND todo_date = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setDate(2, Date.valueOf(date));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void markAsDone(String userId, LocalDate date) {
        String sql = "UPDATE TODOS SET done = 'Y' WHERE user_id = ? AND todo_date = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setDate(2, Date.valueOf(date));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
