package ru.job4j.todo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.job4j.todo.dto.TaskDto;
import ru.job4j.todo.model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(source = "priority.id", target = "priorityId")
    TaskDto toTaskDto(Task task);

    @Mapping(source = "priorityId", target = "priority.id")
    Task toTask(TaskDto taskDto);
}