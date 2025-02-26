package com.goit.restapiexample.service;

import com.goit.restapiexample.EnumErrorAuth;
import com.goit.restapiexample.entity.Users;
import com.goit.restapiexample.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private static final int MAX_USER_ID_LENGTH = 100;
    private static final int MAX_PASSWORD_LENGTH = 255;
    private static final int MAX_AGE = 100;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public EnumErrorAuth register(Users user) {
        Users existingUser = userService.findByUsername(user.getUserId());

        if (Objects.nonNull(existingUser)) {
            return EnumErrorAuth.userAlreadyExists;
        }

        Optional<EnumErrorAuth> validationError = validateRegistrationFields(user);

        if (validationError.isPresent()) {
            return validationError.get();
        }

        userService.saveUser(Users.builder()
                .userId(user.getUserId())
                .passwordHash(passwordEncoder.encode(user.getPasswordHash()))
                .name(user.getName())
                .age(user.getAge())
                .build());

        return EnumErrorAuth.ok;
    }

    public ResultAuthService login(Users user) {
        ResultAuthService resultAuthService = new ResultAuthService();

        Optional<EnumErrorAuth> validationError = validateLoginFields(user);

        if (validationError.isPresent()) {
            resultAuthService.setError(validationError.get());
            return resultAuthService;
        }

        Users loginUser = userService.findByUsername(user.getUserId());

        if (Objects.isNull(loginUser)) {
            resultAuthService.setError(EnumErrorAuth.invalidEmail);
            return resultAuthService;
        }

        if (!passwordEncoder.matches(user.getPasswordHash(), loginUser.getPasswordHash())) {
            resultAuthService.setError(EnumErrorAuth.invalidPassword);
            return resultAuthService;
        }

        String authToken = jwtUtil.generateToken(user.getUserId());

        resultAuthService.setError(EnumErrorAuth.ok);
        resultAuthService.setAuthToken(authToken);
        return resultAuthService;
    }

    private Optional<EnumErrorAuth> validateRegistrationFields(Users user) {
        if (Objects.isNull(user.getUserId()) || user.getUserId().length() > MAX_USER_ID_LENGTH) {
            return Optional.of(EnumErrorAuth.invalidEmail);
        }

        if (Objects.isNull(user.getPasswordHash()) || user.getPasswordHash().length() > MAX_PASSWORD_LENGTH) {
            return Optional.of(EnumErrorAuth.invalidPassword);
        }

        if (Objects.isNull(user.getName()) || user.getName().length() > MAX_USER_ID_LENGTH) {
            return Optional.of(EnumErrorAuth.invalidName);
        }

        if (Objects.isNull(user.getAge()) || user.getAge() > MAX_AGE) {
            return Optional.of(EnumErrorAuth.invalidAge);
        }

        return Optional.empty();
    }

    private Optional<EnumErrorAuth> validateLoginFields(Users user) {
        if (Objects.isNull(user.getUserId()) || user.getUserId().length() > MAX_USER_ID_LENGTH) {
            return Optional.of(EnumErrorAuth.invalidEmail);
        }

        if (Objects.isNull(user.getPasswordHash()) || user.getPasswordHash().length() > MAX_PASSWORD_LENGTH) {
            return Optional.of(EnumErrorAuth.invalidPassword);
        }

        return Optional.empty();
    }
}
