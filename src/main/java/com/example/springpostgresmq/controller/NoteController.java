package com.example.springpostgresmq.controller;


import com.example.springpostgresmq.model.Note;
import com.example.springpostgresmq.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.findAll();
    }

    @GetMapping("/{id}")
    public Note getNote(@PathVariable Long id) {
        return noteService.findById(id).orElse(null);
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteService.save(note);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteById(id);
    }
}

