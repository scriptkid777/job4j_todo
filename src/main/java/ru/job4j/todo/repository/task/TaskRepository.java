package ru.job4j.todo.repository.task;

import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.Optional;

public interface TaskRepository {
    Task create(Task task);

    boolean update(Task task);

    boolean deleteById(Integer id);

    Optional<Task> findById(Integer id);

    Collection<Task> findAll();

    Collection<Task> findNew();

    Collection<Task> findCompleted();

    boolean changeStatus(Integer id);
}
