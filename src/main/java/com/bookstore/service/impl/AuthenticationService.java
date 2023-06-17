package com.bookstore.service.impl;

import com.bookstore.config.JwtService;
import com.bookstore.dto.AuthenticationRequest;
import com.bookstore.dto.AuthenticationResponse;
import com.bookstore.dto.RegisterRequest;
import com.bookstore.entity.User;
import com.bookstore.entity.security.Role;
import com.bookstore.exception.UserExistsException;
import com.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserExistsException("Username " + request.getUsername() + " exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserExistsException("Email " + request.getEmail() + " exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        String emailOrUsername = request.getEmailOrUsername();
        User user;
        if (emailOrUsername.matches("^[_a-zA-Z][_a-zA-Z0-9]{2,50}$")) {
            authenticate(emailOrUsername, request.getPassword());
            user = userRepository.findByUsername(emailOrUsername);
        } else if (emailOrUsername.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{1,8}$")) {
            user = userRepository.findByEmail(emailOrUsername)
                    .orElseThrow(
                            () -> new BadCredentialsException("User with provided email doesn't exist")
                    );
            authenticate(user.getUsername(), request.getPassword());
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );
    }
}
