package com.campusflow.backend.service;

import com.campusflow.backend.dto.AuthenticationRequest;
import com.campusflow.backend.dto.AuthenticationResponse;
import com.campusflow.backend.dto.RegisterRequest;
import com.campusflow.backend.model.User;
import com.campusflow.backend.repository.UserRepository;
import com.campusflow.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        // 1. Build the user from the incoming request
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                // 2. Hash the password before saving!
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        // 3. Save to database
        repository.save(user);

        // 4. Generate the secure token
        var jwtToken = jwtService.generateToken(user);

        // 5. Return the token to the user
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // 1. Spring Security checks if the email & password match the database
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // 2. If we reach here, the password is correct. Fetch the user.
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        // 3. Generate a new token for this session
        var jwtToken = jwtService.generateToken(user);

        // 4. Return the token
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}