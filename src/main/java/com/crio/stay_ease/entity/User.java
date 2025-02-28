package com.crio.stay_ease.entity;

import com.crio.stay_ease.util.UserRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRole role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (role) {
            case CUSTOMER -> List.of((GrantedAuthority) ()-> "ROLE_CUSTOMER");
            case ADMIN -> List.of((GrantedAuthority) ()-> "ROLE_ADMIN");
            case MANAGER -> List.of((GrantedAuthority) ()-> "ROLE_MANAGER");
        };
    }


    @Override
    public String getUsername() {
        return email;
    }
}
