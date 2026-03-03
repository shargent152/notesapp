package com.example.notesapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.notesapp.model.Reminder;

public interface reminderREPO extends JpaRepository<Reminder, Long>{
    List<Reminder> findByUserUsername(String username);
}
