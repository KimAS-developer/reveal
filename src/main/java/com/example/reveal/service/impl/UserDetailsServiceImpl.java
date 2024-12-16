package com.example.reveal.service.impl;

import com.example.reveal.config.CustomUserDetails;
import com.example.reveal.domain.User;
import com.example.reveal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(email);
        if (user.isPresent())
            return new CustomUserDetails(user.get());

        throw new UsernameNotFoundException("userName by email: " + email + " not found");
    }
}
