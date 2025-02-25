package com.goit.restapiexample.note.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateNoteRequest {
    private long id;
    private String title;
    private String content;
}
