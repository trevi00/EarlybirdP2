package domain.todo.service;

import domain.todo.model.ToDo;
import domain.todo.repository.ToDoRepository;

import java.time.LocalDate;
import java.util.List;

public class DefaultToDoService implements ToDoService {

    private final ToDoRepository repository;

    public DefaultToDoService(ToDoRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean save(ToDo todo) {
        if (repository.exists(todo.getUserId(), todo.getDate())) {
            return false;
        }
        repository.save(todo);
        return true;
    }

    @Override
    public ToDo getByDate(String userId, LocalDate date) {
        return repository.findByDate(userId, date);
    }

    @Override
    public List<ToDo> getAll(String userId) {
        return repository.findAll(userId);
    }

    @Override
    public void delete(String userId, LocalDate date) {
        repository.delete(userId, date);
    }

    @Override
    public void markAsDone(String userId, LocalDate date) {
        repository.markAsDone(userId, date);
    }
}
