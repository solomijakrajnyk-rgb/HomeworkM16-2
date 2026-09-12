

package com.example.todolist.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoteNotFoundException extends RuntimeException {

    public NoteNotFoundException(long id) {
        super("Note with id " + id + " not found");
    }
}
