package com.goit.restapiexample.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM notes n WHERE n.user_id = :username")
    List<Note> getUserNotes(String username);
}
