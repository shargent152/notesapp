package com.example.notesapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.notesapp.model.Folders;
import com.example.notesapp.model.Note;
import com.example.notesapp.model.Reminder;
import com.example.notesapp.repository.FolderREPO;
import com.example.notesapp.repository.NoteREPO;
import com.example.notesapp.repository.UserREPO;
import com.example.notesapp.repository.reminderREPO;

@Controller
public class searchController {
    
    @Autowired
    private UserREPO repo;
    @Autowired
    private reminderREPO rrepo;
    @Autowired
    private NoteREPO nrepo;
    @Autowired
    private FolderREPO frepo;



    @GetMapping("/search")
    public String home(Model model, Authentication authentication, @RequestParam("search") String search) {
        String username = authentication.getName();
        System.out.println(search);
        List<Reminder> reminders = rrepo.filterByWordsTimeC(username,search);
        List<Note> notes = nrepo.filterByWordsTimeC(username,search);
        model.addAttribute("notes",notes);
        model.addAttribute("reminders",reminders);
        model.addAttribute("searched",search);
        List<Folders> folders = frepo.filterByWordsTimeC(username,search);
        model.addAttribute("folders",folders);
        return "search";
    }
     
    @GetMapping("/search/byCD")
    public String notesFilteredByCD(Model model, Authentication authentication, @RequestParam("search") String search) {
        String username = authentication.getName();
        List<Reminder> reminders = rrepo.filterByWordsTimeC(username,search);
        List<Note> notes = nrepo.filterByWordsTimeC(username,search);
        model.addAttribute("notes",notes);
        model.addAttribute("reminders",reminders);
        model.addAttribute("searched",search);
        List<Folders> folders = frepo.filterByWordsTimeC(username,search);
        model.addAttribute("folders",folders);
        return "search";
    }
    @GetMapping("/search/byDE")
    public String noteFilteredByDE(Model model, Authentication authentication, @RequestParam("search") String search) {
        String username = authentication.getName();
        List<Reminder> reminders = rrepo.filterByWordsTimeE(username, search);
        List<Note> notes = nrepo.filterByWordsTimeE(username,search);
        model.addAttribute("notes",notes);
        model.addAttribute("reminders",reminders);
        model.addAttribute("searched",search);
        List<Folders> folders = frepo.filterByWordsTimeE(username,search);
        model.addAttribute("folders",folders);
        return "search";
    }
    @GetMapping("/search/byT")
    public String notesFilterByT(Model model, Authentication authentication, @RequestParam("search") String search) {
        String username = authentication.getName();
        List<Reminder> reminders = rrepo.filterByWordsTitle(username,search);
        List<Note> notes = nrepo.filterByWordsTitle(username,search);
        model.addAttribute("notes",notes);
        model.addAttribute("reminders",reminders);
        model.addAttribute("searched",search);
        List<Folders> folders = frepo.filterByWordsTitle(username,search);
        model.addAttribute("folders",folders);
        return "search";
    }
}
