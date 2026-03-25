package com.example.notesapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.notesapp.model.Folders;

public interface FolderREPO extends JpaRepository<Folders, Long>{
    List<Folders> findByUserUsername(String username);
    @Query(value = "SELECT n FROM Folders n LEFT JOIN n.user u WHERE u.username = :username ORDER BY timeC DESC")
    List<Folders> filterByTimeC(String username);
    @Query(value = "SELECT n FROM Folders n LEFT JOIN n.user u WHERE u.username = :username ORDER BY timeE DESC")
    List<Folders> filterByTimeE(String username);
    @Query(value = "SELECT n FROM Folders n LEFT JOIN n.user u WHERE u.username = :username ORDER BY LOWER(title)")
    List<Folders> filterByTitle(String username);
    @Query(value = "SELECT n from Folders n LEFT JOIN n.user u WHERE u.username = :username AND n.title LIKE %:filter% ORDER BY timeC DESC")
    List<Folders> filterByWordsTimeC(String username, String filter);
     @Query(value = "SELECT n FROM Folders n LEFT JOIN n.user u WHERE u.username = :username AND n.title LIKE %:filter% ORDER BY timeE DESC")
    List<Folders> filterByWordsTimeE(String username, String filter);
    @Query(value = "SELECT n FROM Folders n LEFT JOIN n.user u WHERE u.username = :username AND n.title LIKE %:filter% ORDER BY LOWER(title)")
    List<Folders> filterByWordsTitle(String username, String filter);
}