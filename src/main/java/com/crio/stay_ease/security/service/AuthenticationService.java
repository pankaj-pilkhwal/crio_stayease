package com.crio.stay_ease.security.service;

import com.crio.stay_ease.entity.User;
import com.crio.stay_ease.exceptions.AlreadyExistException;
import com.crio.stay_ease.respository.UserRepository;
import com.crio.stay_ease.security.dto.JwtTokenDto;
import com.crio.stay_ease.security.dto.LogInDto;
import com.crio.stay_ease.security.dto.SignUpDto;
import com.crio.stay_ease.security.dto.SignUpResponseDto;
import com.crio.stay_ease.util.UserRole;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService, UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }


    public JwtTokenDto logIn(LogInDto logInDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(logInDto.getEmail(), logInDto.getPassword());

        log.info(usernamePassword);
        authenticationManager.authenticate(usernamePassword);

        UserDetails userDetails = userDetailsService.loadUserByUsername(logInDto.getEmail());
        return new JwtTokenDto(tokenService.generateToken(userDetails));
    }


    public SignUpResponseDto signUp(SignUpDto signUpDto) {
        if(userRepository.findByEmail(signUpDto.getEmail()).isPresent()) {
            throw new AlreadyExistException("User already exists");
        }

        User user = userRepository.save(
                User.builder()
                        .email(signUpDto.getEmail())
                        .firstName(signUpDto.getFirstName())
                        .lastName(signUpDto.getLastName())
                        .role(signUpDto.getRole() == null ?  UserRole.CUSTOMER : signUpDto.getRole())
                        .password(passwordEncoder.encode(signUpDto.getPassword()))
                        .build()
        );

        return SignUpResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .build();
    }

}
