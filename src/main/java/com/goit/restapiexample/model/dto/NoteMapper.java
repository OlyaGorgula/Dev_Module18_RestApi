package com.goit.restapiexample.model.dto;

import com.goit.restapiexample.EnumErrorNote;
import com.goit.restapiexample.entity.Note;
import com.goit.restapiexample.model.dto.reqeuest.NoteRequestDto;
import com.goit.restapiexample.model.dto.response.DeleteNoteResponseDto;
import com.goit.restapiexample.model.dto.response.GetUserNotesResponseDto;
import com.goit.restapiexample.model.dto.response.NoteResponseDto;
import com.goit.restapiexample.service.ResultNoteService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class NoteMapper {

    public static Note fromNoteRequestDto(NoteRequestDto request) {
        return Note.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }

    public static NoteResponseDto mapToNoteResponseDto(ResultNoteService resultNoteService) {
        return NoteResponseDto.builder()
                .note(resultNoteService.getNote())
                .error(resultNoteService.getError())
                .build();
    }

    public static GetUserNotesResponseDto mapToGetUserNotesResponseDto(List<Note> notes) {
        return GetUserNotesResponseDto.builder()
                .userNotes(notes)
                .error(EnumErrorNote.ok)
                .build();
    }

    public static DeleteNoteResponseDto mapToDeleteNoteResponseDto(EnumErrorNote error) {
        return DeleteNoteResponseDto.builder()
                .error(error)
                .build();
    }
}
