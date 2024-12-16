package com.example.reveal.service.impl;

import com.example.reveal.domain.ResultUserCreation;
import com.example.reveal.domain.Role;
import com.example.reveal.domain.User;
import com.example.reveal.repository.UserRepository;
import com.example.reveal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;
    private final UserRepository repository;

    @Override
    public ResultUserCreation create(User user) {
        ResultUserCreation result = checkAndConfigUser(user);
        if (result == ResultUserCreation.USER_CREATION_IS_SUCCESSFUL) {
            repository.save(user);
            return result;
        }
        return result;
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void update(User user) {
        repository.save(user);
    }

    @Override
    public void deleteByEmail(String email) {
        repository.deleteByEmail(email);
    }

    @Override
    public void subscribe(User following, User followed) {
        followed.setSubscribers(repository.findSubscribersByChannelId(followed.getId()));
        followed.getSubscribers().add(following);
        repository.save(followed);
    }

    @Override
    public void unsubscribe(User following, User followed) {
        followed.setSubscribers(repository.findSubscribersByChannelId(followed.getId()));
        followed.getSubscribers().remove(following);
        repository.save(followed);
    }

    @Override
    public boolean isFollower(User following, User followed) {
        return repository.findSubscribersByChannelId(followed.getId()).contains(following);
    }

    public ResultUserCreation checkAndConfigUser(User user) {
        Optional<User> nameUser = repository.findByUserName(user.getUserName());
        if (nameUser.isPresent()) {
            return ResultUserCreation.USER_NAME_IS_USED;
        }

        Optional<User> emailUser = repository.findByEmail(user.getEmail());
        if (emailUser.isPresent()) {
            return ResultUserCreation.USER_EMAIL_IS_USED;
        }


        user.setRole(Role.ROLE_USER);
        user.setPassword(encoder.encode(user.getPassword()));
        return ResultUserCreation.USER_CREATION_IS_SUCCESSFUL;
    }
}
