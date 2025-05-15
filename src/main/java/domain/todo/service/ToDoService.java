// domain.todo.service.ToDoService.java
package domain.todo.service;

import domain.todo.model.ToDo;
import java.time.LocalDate;
import java.util.List;

public interface ToDoService {
    boolean create(ToDo todo);               // 하루 1건 제한
    void markAsDone(String userId, LocalDate date);
    List<ToDo> getAll(String userId);
    void delete(String userId, LocalDate date);
}
