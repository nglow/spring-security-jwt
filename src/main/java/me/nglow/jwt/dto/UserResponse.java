package me.nglow.jwt.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.nglow.jwt.entity.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserResponse {

    private String username;

    private String password;

    private String nickname;

    public static UserResponse from(User user) {
        var response = new UserResponse();
        response.username = user.getEmail();
        response.password = user.getPassword();
        response.nickname = user.getName();

        return response;
    }
}
