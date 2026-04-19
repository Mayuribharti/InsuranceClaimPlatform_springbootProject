package com.security.auth.security;

import com.security.auth.DTO.LoginRequestDto;
import com.security.auth.DTO.LoginResponseDto;
import com.security.auth.DTO.SignupResponseDto;
import com.security.auth.Role;
import com.security.auth.User;
import com.security.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto(token, user.getId());
    }

    public  SignupResponseDto signUp(LoginRequestDto signupResponseDto) {
        User user = userRepository.findByUsername(signupResponseDto.getUsername()).orElse(null);
        if (user != null) throw new IllegalArgumentException("User Already exists"); // User already exists

        user = userRepository.save(User.builder()
                .role(Role.USER)
                .username(signupResponseDto.getUsername())
                .password(passwordEncoder.encode(signupResponseDto.getPassword()))
                .build()
        );
return new SignupResponseDto(user.getId(), user.getUsername());
    }
}
