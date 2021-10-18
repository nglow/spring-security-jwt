package me.nglow.jwt.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LoginDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String email;

    @NotNull
    @Size(min = 3, max = 50)
    private String password;
}
