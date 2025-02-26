package com.goit.restapiexample.controller;

import com.goit.restapiexample.model.dto.NoteMapper;
import com.goit.restapiexample.model.dto.response.*;
import com.goit.restapiexample.service.NoteService;
import com.goit.restapiexample.model.dto.reqeuest.NoteRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    public NoteResponseDto create(Principal principal, @RequestBody NoteRequestDto request) {
        return NoteMapper.mapToNoteResponseDto(
                noteService.create(principal.getName(), NoteMapper.fromNoteRequestDto(request))
        );
    }

    @GetMapping
    public GetUserNotesResponseDto getUserNotes(Principal principal) {
        return NoteMapper.mapToGetUserNotesResponseDto(
                noteService.getUserNotes(principal.getName())
        );
    }

    @PatchMapping
    public NoteResponseDto update(Principal principal,
                                    @RequestParam(name = "id") long id,
                                    @RequestBody NoteRequestDto request) {
        return NoteMapper.mapToNoteResponseDto(
                noteService.update(principal.getName(), NoteMapper.fromNoteRequestDto(request), id)
        );
    }

    @DeleteMapping
    public DeleteNoteResponseDto delete(Principal principal, @RequestParam(name = "id") long id) {
        return NoteMapper.mapToDeleteNoteResponseDto(noteService.delete(principal.getName(), id));
    }
}
