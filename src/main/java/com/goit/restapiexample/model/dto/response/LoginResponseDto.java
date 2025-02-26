package com.goit.restapiexample.model.dto.response;

import com.goit.restapiexample.EnumErrorAuth;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class LoginResponseDto {
    private EnumErrorAuth error;

    private String authToken;
}
