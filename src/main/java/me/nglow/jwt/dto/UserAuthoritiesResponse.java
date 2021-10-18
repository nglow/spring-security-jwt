package me.nglow.jwt.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.nglow.jwt.entity.Authority;
import me.nglow.jwt.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserAuthoritiesResponse {

    private String email;

    private String password;

    private String name;

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
        response.email = user.getEmail();
        response.password = user.getPassword();
        response.name = user.getName();
        response.authorities = authorities.stream().map(x -> AuthoritiesResponse.of(x.getName())).collect(Collectors.toList());

        return response;
    }
}
