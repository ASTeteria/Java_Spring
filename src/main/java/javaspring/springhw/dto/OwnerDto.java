package javaspring.springhw.dto;

import lombok.Builder;

@Builder
public record OwnerDto(
        Long id,
        String username,
        String email
) {}