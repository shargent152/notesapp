package com.example.notesapp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
public class Folders {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private LocalDateTime timeC;

    private LocalDateTime timeE;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy="folder", cascade=CascadeType.ALL)
    private List<Note> note = new ArrayList<>();

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

    public LocalDateTime getTimeC() {
        return timeC;
    }

    public void setTimeC(LocalDateTime timeC) {
        this.timeC = timeC;
    }

    public LocalDateTime getTimeE() {
        return timeE;
    }

    public void setTimeE(LocalDateTime timeE) {
        this.timeE = timeE;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Note> getNote() {
        return note;
    }

    public void setNote(List<Note> note) {
        this.note = note;
    }
}
