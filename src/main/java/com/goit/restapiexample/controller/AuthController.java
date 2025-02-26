package com.goit.restapiexample.controller;

import com.goit.restapiexample.model.dto.AuthMapper;
import com.goit.restapiexample.model.dto.reqeuest.LoginRequestDto;
import com.goit.restapiexample.model.dto.reqeuest.RegistrationRequestDto;
import com.goit.restapiexample.model.dto.response.LoginResponseDto;
import com.goit.restapiexample.model.dto.response.RegistrationResponseDto;
import com.goit.restapiexample.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public RegistrationResponseDto register(@RequestBody RegistrationRequestDto request) {
        return AuthMapper.mapToRegistrationResponseDto(authService.register(AuthMapper.fromRegistrationRequestDto(request)));
    }

    @PostMapping("/login")
    public LoginResponseDto register(@RequestBody LoginRequestDto request) {
        return AuthMapper.mapToLoginResponseDto(authService.login(AuthMapper.fromLoginRequestDto(request)));
    }
}
