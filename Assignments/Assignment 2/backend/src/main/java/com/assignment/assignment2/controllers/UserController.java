package com.assignment.assignment2.controllers;

import com.assignment.assignment2.controllers.dto.DeleteRequest;
import com.assignment.assignment2.user.UserService;
import com.assignment.assignment2.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.assignment.assignment2.UrlMapping.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(FIND_ALL)
    @Secured({"ADMIN"})
    public List<UserDTO> allItems() {
        return userService.findAll();
    }

    @PostMapping(DELETE)
    @Secured({"ADMIN"})
    public void delete(@Valid @RequestBody DeleteRequest deleteRequest) {
        userService.deleteByID(deleteRequest.getId());
    }

    @PostMapping(SAVE)
    @Secured({"ADMIN"})
    public void save(@Valid @RequestBody UserDTO userDTO) {
        userService.save(userDTO);
    }

    @PostMapping(UPDATE)
    @Secured({"ADMIN"})
    public void update(@Valid @RequestBody UserDTO userDTO) {
        userService.update(userDTO);
    }

    @GetMapping(FIND_BY_ID)
    @Secured({"ADMIN"})
    public UserDTO findById(@RequestParam Long id) {
        return userService.findById(id);
    }
}