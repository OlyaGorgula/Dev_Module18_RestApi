package com.goit.restapiexample.service;

import com.goit.restapiexample.entity.Note;
import com.goit.restapiexample.EnumErrorNote;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ResultNoteService {
    private EnumErrorNote error;

    private List<Note> notes;

    private Note note;
}
