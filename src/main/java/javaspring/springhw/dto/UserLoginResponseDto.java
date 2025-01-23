package javaspring.springhw.dto;

import lombok.Builder;

@Builder
public record UserLoginResponseDto(
        String accessToken,
        String refreshToken
) {}
