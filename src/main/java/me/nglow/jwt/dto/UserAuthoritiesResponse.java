package me.nglow.jwt.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.nglow.jwt.entity.Authority;
import me.nglow.jwt.entity.User;
import me.nglow.jwt.entity.UserAuthority;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserAuthoritiesResponse {

    private String username;

    private String password;

    private String nickname;

    List<AuthoritiesResponse> authorities;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    private static class AuthoritiesResponse {
        private String name;

        public static AuthoritiesResponse of(String name) {
            var response = new AuthoritiesResponse();
            response.name = name;

            return response;
        }
    }

    public static UserAuthoritiesResponse from(User user, List<Authority> authorities) {
        var response = new UserAuthoritiesResponse();
        response.username = user.getUsername();
        response.password = user.getPassword();
        response.nickname = user.getNickname();
        response.authorities = authorities.stream().map(x -> AuthoritiesResponse.of(x.getName())).collect(Collectors.toList());

        return response;
    }
}
