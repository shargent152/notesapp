package com.example.notesapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.notesapp.model.Note;

public interface NoteREPO extends JpaRepository<Note, Long>{
    List<Note> findByUserUsername(String username);
}
