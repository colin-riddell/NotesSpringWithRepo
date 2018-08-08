package com.example.easynotes;

import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import java.nio.charset.Charset;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyNotesApplicationTests {

	private MockMvc mockMvc;

	private Note note;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	@Autowired // Inject a version of the NoteRepository here
	NoteRepository noteRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup(){
		this.mockMvc = webAppContextSetup(webApplicationContext).build();

		note = new Note("1234a", "blah blah blah");
		noteRepository.save(note);
	}

	@Test
	public void contextLoads() {
		//TODO: Find out what this was for
	}

	@Test
	public void testGetNotesWithTitle() throws Exception{
		mockMvc.perform(get("/api/notes/1234a"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect( jsonPath("$[0].title").value("1234a"));
	}

}
