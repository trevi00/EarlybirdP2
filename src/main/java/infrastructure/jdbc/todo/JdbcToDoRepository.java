// infrastructure.jdbc.todo.JdbcToDoRepository.java
package infrastructure.jdbc.todo;

import domain.todo.model.ToDo;
import domain.todo.repository.ToDoRepository;

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
            USING (SELECT ? AS id FROM dual) s
            ON (t.id = s.id)
            WHEN MATCHED THEN
              UPDATE SET title = ?, content = ?, done = ?
            WHEN NOT MATCHED THEN
              INSERT (id, user_id, todo_date, title, content, done)
              VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, todo.getId());
            pstmt.setString(2, todo.getTitle());
            pstmt.setString(3, todo.getContent());
            pstmt.setString(4, todo.isDone() ? "Y" : "N");

            pstmt.setString(5, todo.getId());
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
    public ToDo findByUserIdAndDate(String userId, LocalDate date) {
        String sql = "SELECT * FROM TODOS WHERE user_id = ? AND todo_date = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setDate(2, Date.valueOf(date));
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return map(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean exists(String userId, LocalDate date) {
        String sql = "SELECT COUNT(*) FROM TODOS WHERE user_id = ? AND todo_date = ?";
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
    public List<ToDo> findAllByUserId(String userId) {
        List<ToDo> result = new ArrayList<>();
        String sql = "SELECT * FROM TODOS WHERE user_id = ? ORDER BY todo_date DESC";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
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
    public void update(ToDo updatedToDo) {
        save(updatedToDo); // MERGE로 처리
    }

    private ToDo map(ResultSet rs) throws SQLException {
        return new ToDo(
                rs.getString("id"),
                rs.getString("user_id"),
                rs.getDate("todo_date").toLocalDate(),
                rs.getString("title"),
                rs.getString("content"),
                "Y".equalsIgnoreCase(rs.getString("done"))
        );
    }
}
