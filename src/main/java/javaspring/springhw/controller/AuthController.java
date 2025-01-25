package javaspring.springhw.controller;

import jakarta.validation.Valid;
import javaspring.springhw.dto.RegisterUserDto;
import javaspring.springhw.dto.UserDto;
import javaspring.springhw.dto.UserLoginRequestDto;
import javaspring.springhw.dto.UserLoginResponseDto;
import javaspring.springhw.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контролер для авторизації та реєстрації користувачів.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService; // Сервіс для роботи з авторизацією

    /**
     * Ендпоінт для реєстрації нового користувача.
     *
     * @param registerUserDto DTO з даними для реєстрації.
     * @return Відповідь з даними нового користувача.
     */
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid RegisterUserDto registerUserDto) {
        return ResponseEntity.ok(authService.registerUser(registerUserDto));
    }

    /**
     * Ендпоінт для входу користувача.
     *
     * @param loginRequestDto DTO із запитом на логін.
     * @return Відповідь з Access Token і Refresh Token.
     */
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody @Valid UserLoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.loginUser(loginRequestDto));
    }
}