package com.goit.restapiexample.model.dto.response;

import com.goit.restapiexample.entity.Note;
import com.goit.restapiexample.EnumErrorNote;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class NoteResponseDto {
    private EnumErrorNote error;

    private Note note;
}
