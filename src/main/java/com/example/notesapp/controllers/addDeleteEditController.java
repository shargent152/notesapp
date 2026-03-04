package com.example.notesapp.controllers;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.notesapp.model.Note;
import com.example.notesapp.model.Reminder;
import com.example.notesapp.model.User;
import com.example.notesapp.repository.NoteREPO;
import com.example.notesapp.repository.UserREPO;
import com.example.notesapp.repository.reminderREPO;

@Controller
public class addDeleteEditController{
    @Autowired
    private UserREPO repo;
    @Autowired
    private reminderREPO rrepo;
    @Autowired
    private NoteREPO nrepo;
    
    @GetMapping("/addNotes")
    public String addNotes(Model model) {
        model.addAttribute("note", new Note());
        return "newNote";
    }
    @PostMapping("/addNotes")
    public String saveAddedNotes(@ModelAttribute Note note,Authentication authentication) {
        String username = authentication.getName();
        User user = repo.findByUsername(username).orElseThrow();
        note.setUser(user);
        note.setTimeC(LocalDateTime.now());
        note.setTimeE(LocalDateTime.now());
        nrepo.save(note);
        return "redirect:/";
    }
    @GetMapping("/noteEditor/{id}")
    public String notesEditor(@PathVariable Long id,Model model) {
        model.addAttribute("note", nrepo.findById(id).orElseThrow());
        return "noteseditor";
    }
    @GetMapping("/noteDelete/{id}")
    public String notesdelete(@PathVariable Long id, Authentication authentication) {
        nrepo.deleteById(id);
        return "redirect:/";
    }
    @PostMapping("/noteEditor/{id}")
    public String saveEditedNotes(@PathVariable Long id, @ModelAttribute Note note) {
        Note existingNote = nrepo.findById(id).orElseThrow();
        existingNote.setTimeE(LocalDateTime.now());
        existingNote.setMessage(note.getMessage());
        existingNote.setTitle(note.getTitle());
        nrepo.save(existingNote);
        return "redirect:/";
    }
    @GetMapping("/addReminder")
    public String addReminder(Model model) {
        model.addAttribute("reminder", new Reminder());
        return "newReminder";
    }
    @PostMapping("/addReminder")
    public String postMethodName(@ModelAttribute Reminder reminder,Authentication authentication) {
        String username = authentication.getName();
        User user = repo.findByUsername(username).orElseThrow();
        reminder.setUser(user);
        rrepo.save(reminder);
        System.out.println(reminder.getTime());
        return "redirect:/";
    }
    @GetMapping("/reminderEditor/{id}")
    public String remidnerEditor(@PathVariable Long id,Model model) {
        model.addAttribute("reminder", rrepo.findById(id).orElseThrow());
        return "remindereditor";
    }
    @GetMapping("/reminderDelete/{id}")
    public String reminderdelete(@PathVariable Long id, Authentication authentication) {
        rrepo.deleteById(id);
        return "redirect:/";
    }
    @PostMapping("/reminderEditor/{id}")
    public String updatedReminder(@PathVariable Long id, @ModelAttribute Reminder reminder) {
        Reminder existingReminder = rrepo.findById(id).orElseThrow();
        existingReminder.setTime(reminder.getTime());
        existingReminder.setMessage(reminder.getMessage());
        rrepo.save(reminder);
        return "redirect:/";
    }
}