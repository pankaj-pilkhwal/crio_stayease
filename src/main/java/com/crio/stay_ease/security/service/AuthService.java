package com.crio.stay_ease.security.service;

import com.crio.stay_ease.respository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;


    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Attempting to load user: {}", username);
        return userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email: "+ username + " not found"));
    }

}