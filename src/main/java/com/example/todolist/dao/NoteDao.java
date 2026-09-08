package com.example.todolist.dao;

import com.example.todolist.model.Note;

import java.util.List;

public interface NoteDao {

    List<Note> findAll();

    Note findById(long id);

    Note save(Note note);

    void deleteById(long id);
}
