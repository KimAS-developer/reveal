package com.example.reveal.service.impl;

import com.example.reveal.domain.Message;
import com.example.reveal.domain.User;
import com.example.reveal.repository.MessageRepository;
import com.example.reveal.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    final MessageRepository repository;

    @Override
    public void create(Message message) {
        repository.save(message);
    }

    @Override
    public List<Message> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Message> findByAuthor(User user) {
        return repository.findByAuthor(user);
    }

    @Override
    public List<Message> findByAuthorId(Long id) {
        return repository.findByAuthorId(id);
    }

    @Override
    public List<Message> findByAuthorUserName(String userName) {
        return repository.findByAuthorUserName(userName);
    }


}
