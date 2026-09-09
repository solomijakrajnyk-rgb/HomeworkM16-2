package com.example.todolist;

import com.example.todolist.model.Note;
import com.example.todolist.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TodolistApplicationTests {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldSaveAndFindNote() {
        Note note = new Note("Test note", "Spring Data works");

        Note savedNote = noteRepository.save(note);

        assertNotNull(savedNote.getId());

        Note foundNote = noteRepository.findById(savedNote.getId()).orElseThrow();

        assertEquals("Test note", foundNote.getTitle());
        assertEquals("Spring Data works", foundNote.getContent());
    }
}

