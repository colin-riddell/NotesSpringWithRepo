package com.example.easynotes.controller;

import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired // Inject a version of the NoteRepository here
    NoteRepository noteRepository;

    //Get all notes
    @RequestMapping( method= RequestMethod.GET)
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // Create a new note
    @PostMapping
    public Note createNote(@Valid @RequestBody Note note){
        return noteRepository.save(note);
    }

    @GetMapping("/{noteTitle}")
    public List<Note> readNote(@PathVariable String noteTitle){
        return noteRepository.noteByTitle(noteTitle);
    }

}

