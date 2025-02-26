package com.goit.restapiexample.model.dto.reqeuest;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationRequestDto {
    private String email;
    private String password;
    private String name;
    private int age;
}
