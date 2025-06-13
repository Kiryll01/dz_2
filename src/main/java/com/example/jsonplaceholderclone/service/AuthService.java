package com.example.jsonplaceholderclone.service;

import com.example.jsonplaceholderclone.model.AuthUser;
import com.example.jsonplaceholderclone.repository.AuthUserRepository;
import com.example.jsonplaceholderclone.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(AuthUser authUser) {
        log.info("Attempting to register user with email: {}", authUser.getEmail());
        
        if (authUserRepository.existsByEmail(authUser.getEmail())) {
            log.error("Registration failed: Email {} already exists", authUser.getEmail());
            throw new RuntimeException("Email already registered");
        }

        String encodedPassword = passwordEncoder.encode(authUser.getPasswordHash());
        log.info("Original password: {}, Encoded password: {}", authUser.getPasswordHash(), encodedPassword);
        
        authUser.setPasswordHash(encodedPassword);
        AuthUser savedUser = authUserRepository.save(authUser);
        log.info("Successfully registered user with email: {}", savedUser.getEmail());
        
        return jwtService.generateToken(savedUser);
    }

    public String login(String email, String password) {
        log.info("Attempting to login user with email: {}", email);
        
        try {
            // First check if user exists
            var user = authUserRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("Login failed: User not found with email: {}", email);
                    return new RuntimeException("User not found");
                });
            
            log.info("Found user: {}, stored password hash: {}", user.getEmail(), user.getPasswordHash());
            
            // Try to authenticate
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );
            
            log.info("Successfully logged in user with email: {}", email);
            return jwtService.generateToken(user);
            
        } catch (BadCredentialsException e) {
            log.error("Login failed: Invalid credentials for email: {}", email);
            throw new RuntimeException("Invalid email or password");
        } catch (Exception e) {
            log.error("Login failed: Unexpected error for email: {}", email, e);
            throw new RuntimeException("Authentication failed: " + e.getMessage());
        }
    }
} 