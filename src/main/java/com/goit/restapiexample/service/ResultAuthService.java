package com.goit.restapiexample.service;

import com.goit.restapiexample.EnumErrorAuth;
import com.goit.restapiexample.EnumErrorNote;
import com.goit.restapiexample.entity.Note;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ResultAuthService {
    private EnumErrorAuth error;

    private String authToken;
}
