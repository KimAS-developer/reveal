package com.example.reveal.service;

import com.example.reveal.domain.Message;
import com.example.reveal.domain.User;

import java.util.List;

public interface MessageService {
    void create(Message message);
    List<Message> findAll();
    List<Message> findByAuthor(User user);
    List<Message> findByAuthorId(Long id);
    List<Message> findByAuthorUserName(String userName);
}
