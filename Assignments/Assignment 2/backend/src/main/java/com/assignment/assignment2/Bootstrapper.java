package com.assignment.assignment2;

import com.assignment.assignment2.bookstore.BookRepository;
import com.assignment.assignment2.bookstore.GenreRepository;
import com.assignment.assignment2.bookstore.model.EGenre;
import com.assignment.assignment2.bookstore.model.Genre;
import com.assignment.assignment2.user.RoleRepository;
import com.assignment.assignment2.user.UserRepository;
import com.assignment.assignment2.user.model.ERole;
import com.assignment.assignment2.user.model.Role;
import com.assignment.assignment2.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if(bootstrap) {
            userRepository.deleteAll();
            roleRepository.deleteAll();
            bookRepository.deleteAll();
            genreRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            for (EGenre value : EGenre.values()) {
                genreRepository.save(
                        Genre.builder()
                            .name(value)
                            .build()
                );
            }

            User user = User.builder()
                    .username("admin")
                    .email("x@x.com")
                    .password(passwordEncoder.encode("admin"))
                    .build();

            Role role = roleRepository.findByName(ERole.ADMIN)
                    .orElseThrow(() -> new RuntimeException("Cannot find EMPLOYEE role"));

            user.setRole(role);
            userRepository.save(user);

            user = User.builder()
                    .username("admin2")
                    .email("x2@x2.com")
                    .password(passwordEncoder.encode("admin"))
                    .build();

            user.setRole(role);
            userRepository.save(user);

            user = User.builder()
                    .username("employee")
                    .email("e@e.com")
                    .password(passwordEncoder.encode("employee"))
                    .build();

            role = roleRepository.findByName(ERole.EMPLOYEE)
                    .orElseThrow(() -> new RuntimeException("Cannot find EMPLOYEE role"));

            user.setRole(role);
            userRepository.save(user);
        }
    }
}