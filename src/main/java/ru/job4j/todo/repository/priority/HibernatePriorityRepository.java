package ru.job4j.todo.repository.priority;

import ru.job4j.todo.model.Priority;
import ru.job4j.todo.repository.CrudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernatePriorityRepository implements PriorityRepository{
    private final CrudRepository crudRepository;

    @Override
    public Optional<Priority> findById(Integer id) {
        return crudRepository.optional(
                "FROM Priority WHERE id = :id",
                Priority.class,
                Map.of("id", id)
        );
    }

    @Override
    public Collection<Priority> findAll() {
        return crudRepository.query("FROM Priority", Priority.class);
    }
}
