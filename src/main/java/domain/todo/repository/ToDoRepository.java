package domain.todo.repository;

import domain.todo.model.ToDo;

import java.time.LocalDate;
import java.util.List;

public interface ToDoRepository {

    void save(ToDo todo);

    boolean exists(String userId, LocalDate date);

    ToDo findByDate(String userId, LocalDate date);

    List<ToDo> findAll(String userId);

    void delete(String userId, LocalDate date);

    void markAsDone(String userId, LocalDate date);
}
