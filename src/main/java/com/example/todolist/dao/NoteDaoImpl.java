package com.example.todolist.dao;

import com.example.todolist.model.Note;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class NoteDaoImpl implements NoteDao {

    private final Map<Long, Note> notes = new HashMap<>();

    public NoteDaoImpl() {
        save(new Note(1, "First note", "This is the first note."));
        save(new Note(2, "Second note", "This is the second note."));
    }

    @Override
    public List<Note> findAll() {
        return new ArrayList<>(notes.values());
    }

    @Override
    public Note findById(long id) {
        Note note = notes.get(id);
        if (note == null) {
            throw new NoSuchElementException("Note with id " + id + " not found");
        }
        return note;
    }

    @Override
    public Note save(Note note) {
        if (note.getId() == 0) {
            long id;
            do {
                id = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
            } while (notes.containsKey(id));
            note.setId(id);
        }
        notes.put(note.getId(), note);
        return note;
    }

    @Override
    public void deleteById(long id) {
        if (!notes.containsKey(id)) {
            throw new NoSuchElementException("Note with id " + id + " not found");
        }
        notes.remove(id);
    }
}
