package me.nglow.jwt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Email
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;


}
