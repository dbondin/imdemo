package ru.stm.imdemo.server.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.stm.imdemo.server.Entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}
