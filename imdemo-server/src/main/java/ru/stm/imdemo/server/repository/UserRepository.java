package ru.stm.imdemo.server.repository;

/**
 * Репоизторий для пользователя
 */

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.stm.imdemo.server.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	Optional<User> findBySub(String sub);
}
