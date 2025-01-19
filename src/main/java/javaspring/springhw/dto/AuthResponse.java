package javaspring.springhw.dto;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {
}
