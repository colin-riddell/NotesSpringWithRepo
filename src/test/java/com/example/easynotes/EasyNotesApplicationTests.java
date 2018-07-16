package com.example.easynotes;

import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyNotesApplicationTests {

	@Autowired // Inject a version of the NoteRepository here
	NoteRepository noteRepository;

	@Test
	public void contextLoads() {
		Note note = new Note("1234a", "blah blah blah");
		noteRepository.save(note);

        List<Note> foundNotesByTitle =  noteRepository.noteByTitle("1234a");

	}

}
