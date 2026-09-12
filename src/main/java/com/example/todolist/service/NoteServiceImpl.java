package com.example.todolist.service;

import com.example.todolist.dao.NoteDao;
import com.example.todolist.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteDao noteDao;

    public NoteServiceImpl(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public List<Note> listAll() {
        return noteDao.findAll();
    }

    @Override
    public Note add(Note note) {
        return noteDao.save(note);
    }

    @Override
    public Note getById(long id) {
        return noteDao.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id));
    }

    @Override
    public void update(long id, Note note) {
        Note existingNote = getById(id);
        existingNote.setTitle(note.getTitle());
        existingNote.setContent(note.getContent());
        noteDao.save(existingNote);
    }

    @Override
    public void deleteById(long id) {
        getById(id);
        noteDao.deleteById(id);
    }
}
