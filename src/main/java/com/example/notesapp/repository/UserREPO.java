package com.example.notesapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.notesapp.model.User;

@Repository
public interface UserREPO extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
    
}
