package com.example.todolist.service;

import com.example.todolist.model.Note;

import java.util.List;

public interface NoteService {

    List<Note> listAll();

    Note add(Note note);

    Note getById(long id);

    void update(long id, Note note);

    void deleteById(long id);
}
