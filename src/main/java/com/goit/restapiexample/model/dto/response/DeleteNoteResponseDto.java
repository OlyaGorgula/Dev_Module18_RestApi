package com.goit.restapiexample.model.dto.response;

import com.goit.restapiexample.EnumErrorNote;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class DeleteNoteResponseDto {
    private EnumErrorNote error;
}
