// domain.todo.repository.ToDoRepository.java
package domain.todo.repository;

import domain.todo.model.ToDo;
import java.time.LocalDate;
import java.util.List;

public interface ToDoRepository {
    void save(ToDo todo);
    ToDo findByUserIdAndDate(String userId, LocalDate date);
    List<ToDo> findAllByUserId(String userId);
    void delete(String userId, LocalDate date);
    boolean exists(String userId, LocalDate date);
    void update(ToDo updatedToDo); // 예: markAsDone
}
