package com.example.easynotes.repository;

import com.example.easynotes.model.Note;

import java.util.List;

public interface NoteRepositoryCustom {
    List<Note> noteByTitle(String title);
}
