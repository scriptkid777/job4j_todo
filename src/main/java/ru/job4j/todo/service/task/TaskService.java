package ru.job4j.todo.service.task;

import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.Optional;

public interface TaskService {
    Task create(Task task);

    boolean update(Task task);

    void deleteById(Integer id);

    Optional<Task> findById(Integer id);

    Collection<Task> findAll();

    Collection<Task> findNew();

    Collection<Task> findCompleted();

    void complete(Task task);
}
