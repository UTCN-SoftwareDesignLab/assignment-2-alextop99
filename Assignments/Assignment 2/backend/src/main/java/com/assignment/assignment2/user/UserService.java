package com.assignment.assignment2.user;

import com.assignment.assignment2.user.dto.UserDTO;
import com.assignment.assignment2.user.mapper.UserMapper;
import com.assignment.assignment2.user.model.ERole;
import com.assignment.assignment2.user.model.Role;
import com.assignment.assignment2.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper ::userToUserDTO)
                .collect(Collectors.toList());
    }

    public void update(UserDTO userDTO) {
        if(!userDTO.getPassword().equals("")) {
            userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        }
        else {
            userDTO.setPassword(null);
        }

        save(userDTO);
    }

    public void save(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);

        Role role;

        if (userDTO.getRole() == null) {
            role = roleRepository.findByName(ERole.EMPLOYEE)
                    .orElseThrow(() -> new RuntimeException("Cannot find EMPLOYEE role"));
        } else {
            role = roleRepository.findByName(ERole.valueOf(userDTO.getRole()))
                    .orElseThrow(() -> new RuntimeException("Cannot find role: " + userDTO.getRole()));
        }

        user.setRole(role);
        userRepository.save(user);
    }

    public void deleteByID(long id) {
        userRepository.deleteById(id);
    }

    public UserDTO findById(Long id) {
        Optional<UserDTO> result = userRepository.findById(id).map(userMapper :: userToUserDTO);
        return result.orElse(null);
    }
}
