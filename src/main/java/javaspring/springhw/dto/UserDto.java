package javaspring.springhw.dto;

import lombok.Builder;

@Builder
public record UserDto(
        Long id,
        String username
) {}
