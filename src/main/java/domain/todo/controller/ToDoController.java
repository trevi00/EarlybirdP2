// domain.todo.controller.ToDoController.java
package domain.todo.controller;

import domain.todo.model.ToDo;
import domain.todo.service.ToDoService;

import java.time.LocalDate;
import java.util.List;

public class ToDoController {

    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    public boolean create(String userId, String title, String content, LocalDate date) {
        ToDo todo = new ToDo("todo-" + userId + "-" + date, userId, date, title, content, false);
        return toDoService.create(todo);
    }

    public void complete(String userId, LocalDate date) {
        toDoService.markAsDone(userId, date);
    }

    public List<ToDo> getAll(String userId) {
        return toDoService.getAll(userId);
    }

    public void delete(String userId, LocalDate date) {
        toDoService.delete(userId, date);
    }
}
