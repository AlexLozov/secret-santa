package com.example.demo.Entity.DTO;

import com.example.demo.Entity.Role;

public record UserResponseDTO(
        Integer id,
        String name,
        String email,
        String role
) {
}
