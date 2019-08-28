package ru.stm.imdemo.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.stm.imdemo.server.domain.Message;


public interface MessageRepository extends JpaRepository <Message, Long> {
}
