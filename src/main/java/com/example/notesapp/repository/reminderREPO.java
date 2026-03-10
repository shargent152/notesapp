package com.example.notesapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.notesapp.model.Reminder;

public interface reminderREPO extends JpaRepository<Reminder, Long>{
    List<Reminder> findByUserUsername(String username);
    @Query(value = "SELECT r FROM Reminder r LEFT JOIN r.user u WHERE u.username = :username ORDER BY timeC DESC")
    List<Reminder> filterByTimeC(String username);
    @Query(value = "SELECT r FROM Reminder r LEFT JOIN r.user u WHERE u.username = :username ORDER BY timeE DESC")
    List<Reminder> filterByTimeE(String username);
    @Query(value = "SELECT r FROM Reminder r LEFT JOIN r.user u WHERE u.username = :username ORDER BY LOWER(Message)")
    List<Reminder> filterByTitle(String username);
    @Query(value = "SELECT r from Reminder r LEFT JOIN r.user u WHERE u.username = :username AND r.Message LIKE %:filter% ORDER BY timeC DESC")
    List<Reminder> filterByWordsTimeC(String username, String filter);
    @Query(value = "SELECT r FROM Reminder r LEFT JOIN r.user u WHERE u.username = :username AND r.Message LIKE %:filter% ORDER BY timeE DESC")
    List<Reminder> filterByWordsTimeE(String username, String filter);
    @Query(value = "SELECT r FROM Reminder r LEFT JOIN r.user u WHERE u.username = :username and r.Message LIKE %:filter% ORDER BY LOWER(Message)")
    List<Reminder> filterByWordsTitle(String username, String filter);
}
