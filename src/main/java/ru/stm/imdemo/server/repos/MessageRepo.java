package ru.stm.imdemo.server.repos;

import org.springframework.data.repository.CrudRepository;

import ru.stm.imdemo.server.domain.Message;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {
   List <Message> findByTag(String tag);
}
