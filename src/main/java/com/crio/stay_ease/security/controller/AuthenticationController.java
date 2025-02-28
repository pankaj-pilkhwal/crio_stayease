package com.crio.stay_ease.security.controller;

import com.crio.stay_ease.security.dto.JwtTokenDto;
import com.crio.stay_ease.security.dto.LogInDto;
import com.crio.stay_ease.security.dto.SignUpDto;
import com.crio.stay_ease.security.dto.SignUpResponseDto;
import com.crio.stay_ease.security.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<SignUpResponseDto> register(@RequestBody SignUpDto signUpDto) {
        return new ResponseEntity<>(authenticationService.signUp(signUpDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> logIn(@RequestBody LogInDto logInDto) {
        return new ResponseEntity<>(authenticationService.logIn(logInDto), HttpStatus.OK);
    }

}
