package com.example.notesapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Note {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String Message;

    private LocalDateTime timeC;

    private LocalDateTime timeE;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTimeE() {
        return timeE;
    }

    public void setTimeE(LocalDateTime timeE) {
        this.timeE = timeE;
    }

    public LocalDateTime getTimeC() {
        return timeC;
    }

    public void setTimeC(LocalDateTime timeC) {
        this.timeC = timeC;
    }
}
