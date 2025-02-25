package com.goit.restapiexample.note.controller;

import com.goit.restapiexample.note.NoteService;
import com.goit.restapiexample.note.controller.request.CreateNoteRequest;
import com.goit.restapiexample.note.controller.request.UpdateNoteRequest;
import com.goit.restapiexample.note.controller.response.CreateNoteResponse;
import com.goit.restapiexample.note.controller.response.DeleteNoteResponse;
import com.goit.restapiexample.note.controller.response.GetUserNotesResponse;
import com.goit.restapiexample.note.controller.response.UpdateNoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    public CreateNoteResponse create(Principal principal, @RequestBody CreateNoteRequest request) {
        return noteService.create(principal.getName(), request);
    }

    @GetMapping
    public GetUserNotesResponse getUserNotes(Principal principal) {
        return noteService.getUserNotes(principal.getName());
    }

    @PatchMapping
    public UpdateNoteResponse update(Principal principal, @RequestBody UpdateNoteRequest request) {
        return noteService.update(principal.getName(), request);
    }

    @DeleteMapping
    public DeleteNoteResponse delete(Principal principal, @RequestParam(name = "id") long id) {
        return noteService.delete(principal.getName(), id);
    }
}
