
package com.example.easynotes.controller;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller()
public class NoteViewController {

    @Autowired // Inject a version of the NoteRepository here
    NoteRepository noteRepository;


    @GetMapping("/note")
    public String renderNote(Model model) {
        ArrayList<Note> foundNotesByTitle =  (ArrayList<Note>) noteRepository.noteByTitle("1234a");
        // Now add model attributes to view
        model.addAttribute("title", foundNotesByTitle.get(0).getTitle());
        model.addAttribute("content", foundNotesByTitle.get(0).getContent());
        return "note";
    }
}

