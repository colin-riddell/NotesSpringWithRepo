package com.example.easynotes.controller;

import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired // Inject a version of the NoteRepository here
    NoteRepository noteRepository;

    //Get all notes
    @RequestMapping(value="/notes", method= RequestMethod.GET)
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // Create a new note
    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note){
        return noteRepository.save(note);
    }


}

