package com.classmate.userservice.service;

import com.classmate.userservice.dto.AuthResponseDTO;
import com.classmate.userservice.dto.LoginRequestDTO;
import com.classmate.userservice.dto.RegisterRequestDTO;
import com.classmate.userservice.model.User;
import com.classmate.userservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponseDTO register(RegisterRequestDTO request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        User saved = userRepository.save(user);

        // token generation later
        return new AuthResponseDTO(saved.getUserId(), null);
    }

    public AuthResponseDTO login(LoginRequestDTO request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("invalid credentials"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("invalid credentials");
        }

        // token generation later
        return new AuthResponseDTO(user.getUserId(), null);
    }
}
