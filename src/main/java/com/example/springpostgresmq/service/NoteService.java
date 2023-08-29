package com.example.springpostgresmq.service;


import com.example.springpostgresmq.model.Note;
import com.example.springpostgresmq.repository.NoteRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    public Note save(Note note) {
        Note savedNote = noteRepository.save(note);

        // Send a message to RabbitMQ after saving the note
        rabbitTemplate.convertAndSend("myQueue", "Note with ID " + savedNote.getId() + " has been added.");

        return savedNote;
    }
}

