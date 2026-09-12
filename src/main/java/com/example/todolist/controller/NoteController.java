package com.example.todolist.controller;

import com.example.todolist.dto.NoteRequestDto;
import com.example.todolist.dto.NoteResponseDto;
import com.example.todolist.mapper.NoteMapper;
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
    private final NoteMapper noteMapper;

    public NoteController(NoteService noteService, NoteMapper noteMapper) {
        this.noteService = noteService;
        this.noteMapper = noteMapper;
    }

    @GetMapping
    public ResponseEntity<List<NoteResponseDto>> getAllNotes() {
        return ResponseEntity.ok(
                noteService.listAll()
                        .stream()
                        .map(noteMapper::toDto)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponseDto> getNoteById(@PathVariable long id) {
        return ResponseEntity.ok(
                noteMapper.toDto(noteService.getById(id))
        );
    }

    @PostMapping
    public ResponseEntity<NoteResponseDto> createNote(
            @Valid @RequestBody NoteRequestDto request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(noteMapper.toDto(noteService.add(noteMapper.fromDto(request))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponseDto> updateNote(
            @PathVariable long id,
            @Valid @RequestBody NoteRequestDto request) {

        noteService.update(id, noteMapper.fromDto(request));

        return ResponseEntity.ok(
                noteMapper.toDto(noteService.getById(id))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable long id) {
        noteService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
