package com.goit.restapiexample.model.dto;

import com.goit.restapiexample.EnumErrorAuth;
import com.goit.restapiexample.entity.Users;
import com.goit.restapiexample.model.dto.reqeuest.LoginRequestDto;
import com.goit.restapiexample.model.dto.reqeuest.RegistrationRequestDto;
import com.goit.restapiexample.model.dto.response.LoginResponseDto;
import com.goit.restapiexample.model.dto.response.RegistrationResponseDto;
import com.goit.restapiexample.service.ResultAuthService;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthMapper {

    public static Users fromRegistrationRequestDto(RegistrationRequestDto request) {
        return Users.builder()
                .userId(request.getEmail())
                .passwordHash(request.getPassword())
                .name(request.getName())
                .age(request.getAge())
                .build();
    }

    public static Users fromLoginRequestDto(LoginRequestDto request) {
        return Users.builder()
                .userId(request.getEmail())
                .passwordHash(request.getPassword())
                .build();
    }

    public static RegistrationResponseDto mapToRegistrationResponseDto(EnumErrorAuth error) {
        return RegistrationResponseDto.builder()
                .error(error)
                .build();
    }

    public static LoginResponseDto mapToLoginResponseDto(ResultAuthService resultAuthService) {
        return LoginResponseDto.builder()
                .error(resultAuthService.getError())
                .authToken(resultAuthService.getAuthToken())
                .build();
    }
}
