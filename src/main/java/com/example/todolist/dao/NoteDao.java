package com.example.todolist.dao;

import com.example.todolist.model.Note;

import java.util.List;
import java.util.Optional;

public interface NoteDao {

    List<Note> findAll();

    Optional<Note> findById(long id);

    Note save(Note note);

    void deleteById(long id);
}
