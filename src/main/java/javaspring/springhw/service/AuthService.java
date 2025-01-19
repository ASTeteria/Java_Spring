package javaspring.springhw.service;

import javaspring.springhw.dto.RegisterUserDto;
import javaspring.springhw.dto.UserDto;
import javaspring.springhw.dto.UserLoginRequestDto;
import javaspring.springhw.dto.UserLoginResponseDto;
import javaspring.springhw.entity.User;
import javaspring.springhw.mapper.UserMapper;
import javaspring.springhw.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final CustomUserDetailsService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserDto registerUser(RegisterUserDto dto) {

        User user = userMapper.mapToEntity(dto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User registeredUser = userService.save(user);


        return UserDto.builder()
                .id(registeredUser.getId())
                .username(registeredUser.getUsername())
                .build();
    }

    public UserLoginResponseDto loginUser(UserLoginRequestDto dto) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());

        authentication = authenticationManager.authenticate(authentication);


        if (authentication.isAuthenticated()) {

            UserDetails user = userService.loadUserByUsername(dto.username());

            String accessToken = jwtUtil.generateAccessToken(user);
            String refreshToken = jwtUtil.generateRefreshToken(user);

            return UserLoginResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        } else {
            throw new BadCredentialsException("Credentials are not valid");
        }
    }
}
