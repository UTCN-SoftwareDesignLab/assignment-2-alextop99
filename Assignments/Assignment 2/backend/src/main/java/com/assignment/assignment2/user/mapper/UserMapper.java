package com.assignment.assignment2.user.mapper;

import com.assignment.assignment2.user.dto.UserDTO;
import com.assignment.assignment2.user.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(
            target = "role",
            source = "role.name"
    )
    UserDTO userToUserDTO(User user);

    @InheritInverseConfiguration
    User userDTOToUser(UserDTO userDTO);
}
