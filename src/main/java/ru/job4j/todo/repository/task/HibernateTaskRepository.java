package ru.job4j.todo.repository.task;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.CrudRepository;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@Repository
@AllArgsConstructor
public class HibernateTaskRepository implements TaskRepository {

    private final CrudRepository crudRepository;


    public Optional<Task> create(Task task) {
        try {
            crudRepository.run(session -> session.persist(task));
            return Optional.of(task);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Task task) {
        try {
       crudRepository.run(session -> session.merge(task));
       return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return false;
    }


    public boolean  changeStatus(Integer id) {
         return crudRepository.executeUpdate(
                "UPDATE Task SET done = true WHERE id = :id",
                Map.of("id", id)
        );
    }


    @Override
    public boolean deleteById(Integer id) {
        return crudRepository.executeUpdate(
                "DELETE Task WHERE id = :id",
                Map.of("id", id)
        );
    }

    @Override
    public Optional<Task> findById(Integer id) {
        return crudRepository.optional(
                "FROM Task t JOIN FETCH t.priority WHERE t.id = :id",
                Task.class,
                Map.of("id", id)
        );
    }

    @Override
    public Collection<Task> findAll() {
        return crudRepository.query("FROM Task t JOIN FETCH t.priority", Task.class);
    }

    @Override
    public Collection<Task> findNew() {
        return crudRepository.query(
                "FROM Task t JOIN FETCH t.priority WHERE t.created >= :today",
                Task.class,
                Map.of("today", LocalDate.now().atStartOfDay())
        );
    }

    @Override
    public Collection<Task> findCompleted() {
    return crudRepository.query("FROM Task t JOIN FETCH t.priority WHERE t.done = true", Task.class);
    }
}
