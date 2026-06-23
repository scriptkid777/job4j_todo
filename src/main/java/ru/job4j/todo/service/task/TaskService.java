package ru.job4j.todo.service.task;

import ru.job4j.todo.dto.TaskDto;
import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.Optional;

public interface TaskService {
    Optional<Task> create(TaskDto taskDto);

    boolean update(TaskDto taskDto);

    boolean deleteById(Integer id);

    Optional<Task> findById(Integer id);

    Collection<Task> findAll();

    Collection<Task> findNew();

    Collection<Task> findCompleted();

    boolean changeStatus(Integer id);
}
