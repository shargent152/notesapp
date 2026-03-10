package com.example.notesapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.notesapp.model.Note;
import com.example.notesapp.model.Reminder;


public interface NoteREPO extends JpaRepository<Note, Long>{
    List<Note> findByUserUsername(String username);
    @Query(value = "SELECT n FROM Note n LEFT JOIN n.user u WHERE u.username = :username ORDER BY timeC DESC")
    List<Note> filterByTimeC(String username);
    @Query(value = "SELECT n FROM Note n LEFT JOIN n.user u WHERE u.username = :username ORDER BY timeE DESC")
    List<Note> filterByTimeE(String username);
    @Query(value = "SELECT n FROM Note n LEFT JOIN n.user u WHERE u.username = :username ORDER BY LOWER(title)")
    List<Note> filterByTitle(String username);
    @Query(value = "SELECT n from Note n LEFT JOIN n.user u WHERE u.username = :username AND n.title LIKE %:filter% ORDER BY timeC DESC")
    List<Note> filterByWordsTimeC(String username, String filter);
     @Query(value = "SELECT n FROM Note n LEFT JOIN n.user u WHERE u.username = :username AND n.title LIKE %:filter% ORDER BY timeE DESC")
    List<Note> filterByWordsTimeE(String username, String filter);
    @Query(value = "SELECT n FROM Note n LEFT JOIN n.user u WHERE u.username = :username AND n.title LIKE %:filter% ORDER BY LOWER(title)")
    List<Note> filterByWordsTitle(String username, String filter);
}
