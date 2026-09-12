

package com.example.todolist.controller;

import com.example.todolist.dto.CreateNoteRequest;
import com.example.todolist.dto.UpdateNoteRequest;
import com.example.todolist.model.Note;
import com.example.todolist.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(noteService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable long id) {
        return ResponseEntity.ok(noteService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Note> createNote(
            @Valid @RequestBody CreateNoteRequest request) {

        Note note = new Note(request.getTitle(), request.getContent());
        Note createdNote = noteService.add(note);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdNote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(
            @PathVariable long id,
            @Valid @RequestBody UpdateNoteRequest request) {

        Note note = noteService.getById(id);
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());

        noteService.update(note);

        return ResponseEntity.ok(note);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable long id) {
        noteService.getById(id);
        noteService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
