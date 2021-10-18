package me.nglow.jwt.service;

import lombok.RequiredArgsConstructor;
import me.nglow.jwt.dto.UserAuthoritiesResponse;
import me.nglow.jwt.dto.UserDto;
import me.nglow.jwt.dto.UserResponse;
import me.nglow.jwt.entity.Authority;
import me.nglow.jwt.entity.User;
import me.nglow.jwt.entity.UserAuthority;
import me.nglow.jwt.repository.AuthorityRepository;
import me.nglow.jwt.repository.UserAuthorityRepository;
import me.nglow.jwt.repository.UserRepository;
import me.nglow.jwt.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse signup(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RuntimeException("Username transmitted is already being used");
        }
        var user = User.of(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), userDto.getName());
        var authority = authorityRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Can't find authority"));
        var userAuthority = UserAuthority.of(user, authority);
        userRepository.save(user);

        return UserResponse.from(userAuthorityRepository.save(userAuthority).getUser());
    }

    public UserAuthoritiesResponse getUserAuthorities(String username) {
        var user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Can't find user by username transmitted"));
        var authorities = userAuthorityRepository.findByUser(user).stream().map(UserAuthority::getAuthority).collect(Collectors.toList());
        return UserAuthoritiesResponse.from(user, authorities);
    }

    public UserAuthoritiesResponse getMyAuthorities() {
        var currentUsername = SecurityUtil.getCurrentUsername()
                .orElseThrow(() -> new RuntimeException("Can't find username of current user"));
        return getUserAuthorities(currentUsername);
    }

}
