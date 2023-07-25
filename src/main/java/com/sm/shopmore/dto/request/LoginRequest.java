package com.sm.shopmore.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @Column(name="email", nullable = false)
    @NotEmpty(message = "Enter your email")
    private String email;
    @Column(name="password", nullable = false)
    @NotEmpty(message = "Enter your password")
    private String password;
}
