package ru.job4j.todo.service.user;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.user.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class HibernateUserService implements UserService {
    private final UserRepository hibernateUserRepository;

    @Override
    public Optional<User> save(User user) {
        return hibernateUserRepository.save(user);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return hibernateUserRepository.findByLoginAndPassword(login, password);
    }
}
