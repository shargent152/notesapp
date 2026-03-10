package com.example.notesapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.notesapp.model.Note;
import com.example.notesapp.model.Reminder;
import com.example.notesapp.repository.NoteREPO;
import com.example.notesapp.repository.UserREPO;
import com.example.notesapp.repository.reminderREPO;



@Controller
public class Main {
   
    @Autowired
    private UserREPO repo;
    @Autowired
    private reminderREPO rrepo;
    @Autowired
    private NoteREPO nrepo;



    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        String username = authentication.getName();
        List<Reminder> reminders = rrepo.filterByTimeC(username);
        List<Note> notes = nrepo.filterByTimeC(username);
        model.addAttribute("notes",notes);
        model.addAttribute("reminders",reminders);
        return "main";
    }
    @GetMapping("/byCD")
    public String notesFilteredByCD(Model model, Authentication authentication) {
        String username = authentication.getName();
        List<Reminder> reminders = rrepo.filterByTimeC(username);
        List<Note> notes = nrepo.filterByTimeC(username);
        model.addAttribute("notes",notes);
        model.addAttribute("reminders",reminders);
        
        return "main";
    }
    @GetMapping("/byDE")
    public String noteFilteredByDE(Model model, Authentication authentication) {
        String username = authentication.getName();
        List<Reminder> reminders = rrepo.filterByTimeE(username);
        List<Note> notes = nrepo.filterByTimeE(username);
        model.addAttribute("notes",notes);
        model.addAttribute("reminders",reminders);
        
        return "main";
    }
    @GetMapping("/byT")
    public String notesFilterByT(Model model, Authentication authentication) {
        String username = authentication.getName();
        List<Reminder> reminders = rrepo.filterByTitle(username);
        List<Note> notes = nrepo.filterByTitle(username);
        model.addAttribute("notes",notes);
        model.addAttribute("reminders",reminders);
        
        return "main";
    }
    
    
    
    
    
    
    
    
    
    

    

    
    
}
