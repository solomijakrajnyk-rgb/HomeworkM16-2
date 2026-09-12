package com.example.todolist.dao;

import com.example.todolist.model.Note;
import com.example.todolist.repository.NoteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NoteDaoImpl implements NoteDao {

    private final NoteRepository noteRepository;

    public NoteDaoImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public Optional<Note> findById(long id) {
        return noteRepository.findById(id);
    }

    @Override
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }
}
