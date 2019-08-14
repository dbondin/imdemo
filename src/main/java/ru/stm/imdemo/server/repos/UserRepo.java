package ru.stm.imdemo.server.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.stm.imdemo.server.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
