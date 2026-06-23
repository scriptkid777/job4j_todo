package ru.job4j.todo.service.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.dto.TaskDto;
import ru.job4j.todo.mappers.TaskMapper;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.priority.PriorityRepository;
import ru.job4j.todo.repository.task.HibernateTaskRepository;
import ru.job4j.todo.repository.task.TaskRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class HibernateTaskService implements TaskService {

    private final  TaskRepository hibernateTaskRepository;
    private final PriorityRepository hibernatePriorityRepository;
    private final TaskMapper taskMapper;

    @Override
    public Optional<Task> create(TaskDto taskDto) {
        return hibernateTaskRepository.create(getTaskFromTaskDto(taskDto));
    }

    @Override
    public boolean update(TaskDto taskDto) {
        return hibernateTaskRepository.update(getTaskFromTaskDto(taskDto));
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

    private Task getTaskFromTaskDto(TaskDto taskDto) {
        Task task = taskMapper.toTask(taskDto);
        var priority = hibernatePriorityRepository.findById(taskDto.getPriorityId()).orElseThrow();
        task.setPriority(priority);
        return task;
    }
}
