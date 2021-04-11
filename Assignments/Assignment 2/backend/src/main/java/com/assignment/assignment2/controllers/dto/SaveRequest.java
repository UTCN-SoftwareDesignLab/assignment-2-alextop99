package com.assignment.assignment2.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SaveRequest {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
}
