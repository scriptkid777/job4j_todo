package ru.job4j.todo.service.priority;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.repository.priority.PriorityRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HibernatePriorityService implements PriorityService {
    private final PriorityRepository hibernatePriorityRepository;

    @Override
    public Optional<Priority> findById(Integer id) {
        return hibernatePriorityRepository.findById(id);
    }

    @Override
    public Collection<Priority> findAll() {
        return hibernatePriorityRepository.findAll();
    }
}
