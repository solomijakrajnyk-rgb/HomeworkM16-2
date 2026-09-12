package com.example.todolist.mapper;

import com.example.todolist.dto.NoteRequestDto;
import com.example.todolist.dto.NoteResponseDto;
import com.example.todolist.model.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    public NoteResponseDto toDto(Note note) {
        return new NoteResponseDto(
                note.getId(),
                note.getTitle(),
                note.getContent()
        );
    }

    public Note fromDto(NoteRequestDto dto) {
        return new Note(
                dto.getTitle(),
                dto.getContent()
        );
    }
}
