package com.example.easynotes.repository;

import com.example.easynotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>, NoteRepositoryCustom
{

   @Query(value = "SELECT * FROM notes t WHERE t.title = :title", nativeQuery = true)
   ArrayList<Note> findByTitle(@Param("title") String title);


   List<Note> noteByTitle(String title);
}
