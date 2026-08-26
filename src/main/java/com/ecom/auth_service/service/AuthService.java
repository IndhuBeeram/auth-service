package com.ecom.auth_service.service;

import com.ecom.auth_service.dto.AuthResponse;
import com.ecom.auth_service.dto.LoginRequest;
import com.ecom.auth_service.dto.RegisterRequest;
import com.ecom.auth_service.entity.Role;
import com.ecom.auth_service.entity.User;
import com.ecom.auth_service.exception.EmailAlreadyExistsException;
import com.ecom.auth_service.exception.InvalidCredentialsException;
import com.ecom.auth_service.repository.UserRepository;
import com.ecom.auth_service.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.AuthenticationException;
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    // =========================
    // REGISTER
    // =========================
    public void register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "Email already registered"
            );
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(Role.USER);

        userRepository.save(user);
    }

    // =========================
    // LOGIN
    // =========================
    public AuthResponse login(LoginRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException ex) {
            throw new InvalidCredentialsException(
                    "Invalid email or password"
            );
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found")
                );

        String token = jwtService.generateToken(request.getEmail());

        return new AuthResponse(
                token,
                user.getEmail(),
                user.getRole().name()
        );
    }
}