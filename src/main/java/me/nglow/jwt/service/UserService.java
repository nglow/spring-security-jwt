package me.nglow.jwt.service;

import lombok.RequiredArgsConstructor;
import me.nglow.jwt.dto.UserDto;
import me.nglow.jwt.entity.User;
import me.nglow.jwt.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User signup(UserDto userDto) {
        if (userRepository)
    }

}
