package ru.job4j.todo.service.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.task.HibernateTaskRepository;
import ru.job4j.todo.repository.task.TaskRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class HibernateTaskService implements TaskService {

    private final  TaskRepository hibernateTaskRepository;

    @Override
    public Task create(Task task) {
        return hibernateTaskRepository.create(task);
    }

    @Override
    public boolean update(Task task) {
        return hibernateTaskRepository.update(task);
    }

    @Override
    public boolean deleteById(Integer id) {
       return hibernateTaskRepository.deleteById(id);
    }

    @Override
    public Optional<Task> findById(Integer id) {
        return hibernateTaskRepository.findById(id);
    }

    @Override
    public Collection<Task> findAll() {
        return hibernateTaskRepository.findAll();
    }

    @Override
    public Collection<Task> findNew() {
        return hibernateTaskRepository.findNew();
    }

    @Override
    public Collection<Task> findCompleted() {
        return hibernateTaskRepository.findCompleted();
    }

    @Override
    public boolean changeStatus(Integer id) {
        return hibernateTaskRepository.changeStatus(id);
    }
}
