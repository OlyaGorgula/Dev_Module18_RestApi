package com.goit.restapiexample.service;

import com.goit.restapiexample.EnumErrorNote;
import com.goit.restapiexample.entity.Note;
import com.goit.restapiexample.entity.Users;
import com.goit.restapiexample.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
    private static final int MAX_TITLE_LENGTH = 100;
    private static final int MAX_CONTENT_LENGTH = 1000;

    private final UserService userService;
    private final NoteRepository repository;

    public ResultNoteService create(String username, Note note) {

        ResultNoteService resultNoteService = new ResultNoteService();

        Optional<EnumErrorNote> validationError = validateFields(note);
        if (validationError.isPresent()) {
            resultNoteService.setError(validationError.get());
            return resultNoteService;
        }

        Users user = userService.findByUsername(username);

        Note createdNote = repository.save(Note.builder()
                .user(user)
                .title(note.getTitle())
                .content(note.getContent())
                .build());

        resultNoteService.setNote(createdNote);
        resultNoteService.setError(EnumErrorNote.ok);
        return resultNoteService;
    }

    public List<Note> getUserNotes(String username) {
        return repository.getUserNotes(username);
    }

    public ResultNoteService update(String username, Note note, Long id) {
        ResultNoteService resultNoteService = new ResultNoteService();

        Optional<Note> optionalNote = repository.findById(id);
        if (optionalNote.isEmpty()) {
            resultNoteService.setError(EnumErrorNote.invalidNoteId);
            return resultNoteService;
        }

        Note updateNote = optionalNote.get();

        boolean isNotUserNote = isNotUserNote(username, updateNote);

        if (isNotUserNote) {
            resultNoteService.setError(EnumErrorNote.insufficientPrivileges);
            return resultNoteService;
        }

        Optional<EnumErrorNote> validationError = validateFields(note);

        if (validationError.isPresent()) {
            resultNoteService.setError(validationError.get());
            return resultNoteService;
        }

        updateNote.setTitle(note.getTitle());
        updateNote.setContent(note.getContent());

        repository.save(updateNote);

        resultNoteService.setNote(updateNote);
        resultNoteService.setError(EnumErrorNote.ok);
        return resultNoteService;
    }

    public EnumErrorNote delete(String username, long id) {
        Optional<Note> optionalNote = repository.findById(id);

        if (optionalNote.isEmpty()) {
            return EnumErrorNote.invalidNoteId;
        }

        Note deleteNote = optionalNote.get();

        boolean isNotUserNote = isNotUserNote(username, deleteNote);

        if (isNotUserNote) {
            return EnumErrorNote.insufficientPrivileges;
        }

        repository.delete(deleteNote);

        return EnumErrorNote.ok;
    }

    private Optional<EnumErrorNote> validateFields(Note note) {

        if (Objects.isNull(note.getTitle())) {
            return Optional.of(EnumErrorNote.invalidTitle);
        }

        if (Objects.isNull(note.getContent())) {
            return Optional.of(EnumErrorNote.invalidContent);
        }

        if (note.getTitle().length() > MAX_TITLE_LENGTH) {
            return Optional.of(EnumErrorNote.invalidTitleLength);
        }

        if (note.getContent().length() > MAX_CONTENT_LENGTH) {
            return Optional.of(EnumErrorNote.invalidContentLength);
        }

        return Optional.empty();
    }

    private boolean isNotUserNote(String username, Note note) {
        return !note.getUser().getUserId().equals(username);
    }

}
