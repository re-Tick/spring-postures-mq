package com.example.springpostgresmq.repository;

import com.example.springpostgresmq.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}

