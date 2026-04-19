package com.security.auth.userController;

import com.security.auth.DTO.LoginRequestDto;
import com.security.auth.DTO.LoginResponseDto;
import com.security.auth.DTO.SignupResponseDto;
import com.security.auth.security.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
       return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signUp(@RequestBody LoginRequestDto signupResponseDto){
        return ResponseEntity.ok(authService.signUp(signupResponseDto));
    }
}
