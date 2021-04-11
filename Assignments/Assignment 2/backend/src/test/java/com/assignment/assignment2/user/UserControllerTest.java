package com.assignment.assignment2.user;

import com.assignment.assignment2.BaseControllerTest;
import com.assignment.assignment2.TestCreationFactory;
import com.assignment.assignment2.controllers.UserController;
import com.assignment.assignment2.user.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.assignment.assignment2.UrlMapping.FIND_ALL;
import static com.assignment.assignment2.UrlMapping.USERS;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends BaseControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService userService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        MockitoAnnotations.openMocks(this);
        controller = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void allUsers() throws Exception {
        List<UserDTO> userDTOs = TestCreationFactory.listOf(UserDTO.class);
        when(userService.findAll()).thenReturn(userDTOs);

        ResultActions result = mockMvc.perform(get(USERS + '/' + FIND_ALL));
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(userDTOs));
    }
}