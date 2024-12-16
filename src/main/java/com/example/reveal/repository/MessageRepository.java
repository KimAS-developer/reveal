package com.example.reveal.repository;

import com.example.reveal.domain.Message;
import com.example.reveal.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByAuthor(User user);
    List<Message> findByAuthorId(Long id);
    List<Message> findByAuthorUserName(String userName);
}
