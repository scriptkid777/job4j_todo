package ru.job4j.todo.repository.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;
import org.hibernate.SessionFactory;

import java.util.Optional;

@Repository
@AllArgsConstructor
@Slf4j
public class HibernateUserRepository implements UserRepository {
    private final SessionFactory sf;

    @Override
    public Optional<User> save(User user) {
        var session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return Optional.of(user);
        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            log.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        var session = sf.openSession();
        Optional<User> optionalUser = Optional.empty();
        try {
            session.beginTransaction();
            optionalUser = session.createQuery(
                            "FROM User WHERE login = :login AND password = :password", User.class
                    )
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return optionalUser;
    }

}
