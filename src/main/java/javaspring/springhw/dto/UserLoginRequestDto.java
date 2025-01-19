package javaspring.springhw.dto;


import jakarta.validation.constraints.NotBlank;

public record UserLoginRequestDto(
        @NotBlank
        String username,
        @NotBlank
        String password
) {}

