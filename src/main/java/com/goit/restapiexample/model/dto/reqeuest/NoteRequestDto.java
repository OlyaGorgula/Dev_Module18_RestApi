package com.goit.restapiexample.model.dto.reqeuest;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoteRequestDto {
    private String title;
    private String content;
}
