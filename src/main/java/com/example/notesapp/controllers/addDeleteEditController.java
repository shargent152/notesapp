package com.example.notesapp.controllers;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.notesapp.model.Folders;
import com.example.notesapp.model.Note;
import com.example.notesapp.model.Reminder;
import com.example.notesapp.model.User;
import com.example.notesapp.repository.FolderREPO;
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
    @Autowired
    private FolderREPO frepo;
    
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
        existingNote.setFolder(note.getFolder());
        nrepo.save(existingNote);
        return "redirect:/";
    }
    @GetMapping("/addReminder")
    public String addReminder(Model model) {
        model.addAttribute("reminder", new Reminder());
        return "newReminder";
    }
    @PostMapping("/addReminder")
    public String saveAddedReminder(@ModelAttribute Reminder reminder,Authentication authentication) {
        String username = authentication.getName();
        User user = repo.findByUsername(username).orElseThrow();
        reminder.setUser(user);
        reminder.setTimeC(LocalDateTime.now());
        reminder.setTimeE(LocalDateTime.now());
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
        existingReminder.setTimeE(LocalDateTime.now());
        rrepo.save(existingReminder);
        return "redirect:/";
    }
    @GetMapping("/addFolders")
    public String addingFOlders(Model model, Authentication authentication) {
        model.addAttribute("folder", new Folders());
        String username = authentication.getName();
        User user = repo.findByUsername(username).orElseThrow();
        List<Note> notes = nrepo.filterByTimeC(username);
        model.addAttribute("notes",notes);
        return "newFolder";
    }
    @PostMapping("/addFolders")
    public String addFolders(@ModelAttribute Folders folder, @RequestParam(required = false) List<Long> ids, Authentication authentication) {
        //TODO: process POST request
        String username = authentication.getName();
        User user = repo.findByUsername(username).orElseThrow();
        folder.setTimeC(LocalDateTime.now());
        folder.setTimeE(LocalDateTime.now());
        folder.setUser(user);
        frepo.save(folder);
        if(ids != null){
            for (Long id : ids){
                Note note = nrepo.findById(id).orElseThrow();
                note.setFolder(folder);
                nrepo.save(note);
                System.out.println("I RAN");
            }
        }
        return "redirect:/";
    }
    @GetMapping("/folderEditor/{id}")
    public String editFolderGET(@PathVariable Long id, Authentication authentication, Model model) {
        Folders folder = frepo.findById(id).orElseThrow();
        String username = authentication.getName();
        List<Note> notesOut = nrepo.filterByTimeC(username);
        List<Note> notesIn = folder.getNote();
        model.addAttribute("notesout",notesOut);
        model.addAttribute("notesin",notesIn);
        model.addAttribute("folder",folder);
        return "editFolder";
    }
    @PostMapping("/folderEditor/{id}")
    public String editFolderPOST(@PathVariable Long id,@ModelAttribute Folders folder, @RequestParam(required = false) List<Long> rids, @RequestParam(required = false)List<Long> aids, Authentication authentication) {
        //TODO: process POST request
        Folders eFolders = frepo.findById(id).orElseThrow();
        eFolders.setTimeE(LocalDateTime.now());
        eFolders.setTitle(folder.getTitle());
        eFolders.setNote(folder.getNote());
        frepo.save(eFolders);
         if(aids != null){
            for (Long ids : aids){
                Note note = nrepo.findById(ids).orElseThrow();
                note.setFolder(folder);
                nrepo.save(note);
                System.out.println("I RAN");
            }
        }
        if(rids != null){
            for (Long ids : rids){
                Note note = nrepo.findById(ids).orElseThrow();
                note.setFolder(null);
                nrepo.save(note);
                System.out.println("I RAN");
            }
        }
        return "redirect:/";
    }
    @GetMapping("/folderDelete/{id}")
    public String folderdelete(@PathVariable Long id, Authentication authentication) {
        nrepo.removeFolderFromNotes(id);
        frepo.deleteById(id);

        List<Note> test = nrepo.filterByTimeC(authentication.getName());
        System.out.println("NOTES FOUND: " + test.size());
        return "redirect:/";
    }
    
    
    
    
}