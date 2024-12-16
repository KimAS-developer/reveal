package com.example.reveal.service;

import com.example.reveal.domain.ResultUserCreation;
import com.example.reveal.domain.User;

import java.util.Optional;

public interface UserService {
    ResultUserCreation create(User user);

    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String email);

    void update(User user);

    void deleteByEmail(String email);

    void subscribe(User following, User followed);

    void unsubscribe(User following, User followed);

    boolean isFollower(User following, User followed);
}
