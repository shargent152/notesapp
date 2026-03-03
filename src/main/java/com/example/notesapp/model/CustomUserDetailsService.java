package com.example.notesapp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.notesapp.repository.UserREPO;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserREPO repo;

    @Autowired
    public CustomUserDetailsService(UserREPO repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Username does not exist"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}