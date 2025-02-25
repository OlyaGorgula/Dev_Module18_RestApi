package com.goit.restapiexample.auth.controller;

import com.goit.restapiexample.auth.controller.request.LoginRequest;
import com.goit.restapiexample.auth.controller.request.RegistrationRequest;
import com.goit.restapiexample.auth.controller.response.LoginResponse;
import com.goit.restapiexample.auth.controller.response.RegistrationResponse;
import com.goit.restapiexample.auth.service.AuthService;
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
    public RegistrationResponse register(@RequestBody RegistrationRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse register(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
