package com.assignment.assignment2.user;

import com.assignment.assignment2.user.dto.UserDTO;
import com.assignment.assignment2.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void findAll() {
        int nrUsers = 10;
        List<User> users = new ArrayList<>();
        for (int i = 0; i < nrUsers; i++) {
            User user = User.builder()
                    .username("User " + i)
                    .password(UUID.randomUUID().toString())
                    .email("user" + i + "@gmail.com")
                    .build();
            users.add(user);
            userRepository.save(user);
        }

        List<UserDTO> userDTOS = userService.findAll();

        for (int i = 0; i < nrUsers; i++) {
            assertEquals(users.get(i).getId(), userDTOS.get(i).getId());
            assertEquals(users.get(i).getUsername(), userDTOS.get(i).getUsername());
        }
    }
}