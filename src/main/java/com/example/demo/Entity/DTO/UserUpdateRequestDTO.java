package com.example.demo.Entity.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateRequestDTO (
        @NotBlank @Size(min = 4, max = 64) String name,
        @NotBlank @Size(min = 3, max = 10) String password
){
}
