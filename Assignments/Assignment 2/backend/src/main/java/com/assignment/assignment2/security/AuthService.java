package com.assignment.assignment2.security;

import com.assignment.assignment2.security.dto.SignupRequest;
import com.assignment.assignment2.user.RoleRepository;
import com.assignment.assignment2.user.UserRepository;
import com.assignment.assignment2.user.model.ERole;
import com.assignment.assignment2.user.model.Role;
import com.assignment.assignment2.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;


    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void register(SignupRequest signUpRequest) {
        User user = User.builder()
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .build();

        String roleStr = signUpRequest.getRole();
        Role role;

        if (roleStr == null) {
            role = roleRepository.findByName(ERole.EMPLOYEE)
                    .orElseThrow(() -> new RuntimeException("Cannot find EMPLOYEE role"));
        } else {
            role = roleRepository.findByName(ERole.valueOf(roleStr))
                    .orElseThrow(() -> new RuntimeException("Cannot find role: " + roleStr));
        }

        user.setRole(role);
        userRepository.save(user);
    }
}
