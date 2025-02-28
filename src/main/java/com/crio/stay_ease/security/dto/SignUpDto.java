package com.crio.stay_ease.security.dto;

import com.crio.stay_ease.util.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private UserRole role;
}
