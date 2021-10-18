package me.nglow.jwt.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.nglow.jwt.entity.Authority;
import me.nglow.jwt.entity.User;
import me.nglow.jwt.entity.UserAuthority;
import me.nglow.jwt.entity.UserState;
import me.nglow.jwt.repository.UserAuthorityRepository;
import me.nglow.jwt.repository.UserRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("userDetailsService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserAuthorityRepository userAuthorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find user by username(" + username + ") transmitted"));
        var authorities = userAuthorityRepository.findByUser(user).stream()
                .map(UserAuthority::getAuthority)
                .collect(Collectors.toList());

        return createUser(user, authorities);
    }

    private org.springframework.security.core.userdetails.User createUser(User user, List<Authority> authorities) {
        if (!user.getState().equals(UserState.ACTIVATED)) throw new RuntimeException("User found by username(" + user.getEmail() + ") transmitted is deactivated");
        else if (authorities.isEmpty()) throw new RuntimeException("User doesn't have any authorities");

        List<GrantedAuthority> grantedAuthorities = authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                grantedAuthorities);
    }
}
