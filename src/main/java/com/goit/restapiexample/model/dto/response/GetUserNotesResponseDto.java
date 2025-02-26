package com.goit.restapiexample.model.dto.response;

import com.goit.restapiexample.entity.Note;
import com.goit.restapiexample.EnumErrorNote;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class GetUserNotesResponseDto {
    private EnumErrorNote error;

    private List<Note> userNotes;
}
