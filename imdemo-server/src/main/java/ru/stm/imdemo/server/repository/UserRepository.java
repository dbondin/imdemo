package ru.stm.imdemo.server.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import ru.stm.imdemo.server.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
	User findByUsername(String username);
}
