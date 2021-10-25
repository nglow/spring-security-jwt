package me.nglow.jwt.controller;

import lombok.RequiredArgsConstructor;
import me.nglow.jwt.dto.UserAuthoritiesResponse;
import me.nglow.jwt.dto.UserDto;
import me.nglow.jwt.dto.UserResponse;
import me.nglow.jwt.entity.User;
import me.nglow.jwt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/sign_up")
    public ResponseEntity<UserResponse> signup(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @GetMapping("/users/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserAuthoritiesResponse> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserAuthorities(username));
    }

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserAuthoritiesResponse> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyAuthorities());
    }
}
