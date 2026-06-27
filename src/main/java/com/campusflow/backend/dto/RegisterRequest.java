package com.campusflow.backend.dto;

import com.campusflow.backend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role; // e.g., STUDENT, ORG_ADMIN, SUPER_ADMIN
}