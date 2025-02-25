package com.goit.restapiexample.auth.controller.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationRequest {
    private String email;
    private String password;
    private String name;
    private int age;
}
