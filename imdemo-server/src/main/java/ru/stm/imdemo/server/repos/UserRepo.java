package ru.stm.imdemo.server.repos;
/**
 * Интерфейс ля пользователя.
 * Создан метод поиска пользователя по имени
 */

import org.springframework.data.jpa.repository.JpaRepository;

import ru.stm.imdemo.server.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
