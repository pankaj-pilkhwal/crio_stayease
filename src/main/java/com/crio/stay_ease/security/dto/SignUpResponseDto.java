package com.crio.stay_ease.security.dto;

import com.crio.stay_ease.util.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class SignUpResponseDto {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private UserRole role;
}
