package domain.todo.service;

import domain.todo.model.ToDo;

import java.time.LocalDate;
import java.util.List;

public interface ToDoService {

    boolean save(ToDo todo);

    ToDo getByDate(String userId, LocalDate date);

    List<ToDo> getAll(String userId);

    void delete(String userId, LocalDate date);

    void markAsDone(String userId, LocalDate date);
}
