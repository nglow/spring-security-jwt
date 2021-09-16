package me.nglow.jwt.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TokenDto {

    private String token;

    public static TokenDto of(String token) {
        var tokenDto = new TokenDto();
        tokenDto.token = token;

        return tokenDto;
    }
}
