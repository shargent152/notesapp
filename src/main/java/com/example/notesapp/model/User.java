package com.example.notesapp.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity

public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    
    private String username;
    
    private String password;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Reminder> reminder = new ArrayList<>();

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Note> note = new ArrayList<>();

    public User(){}

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Reminder> getReminder() {
        return reminder;
    }

    public void setReminder(List<Reminder> reminder) {
        this.reminder = reminder;
    }

    public List<Note> getNote() {
        return note;
    }

    public void setNote(List<Note> note) {
        this.note = note;
    }
    }

