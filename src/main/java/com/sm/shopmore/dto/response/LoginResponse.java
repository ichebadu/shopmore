package com.sm.shopmore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String message;
    private String accessToken;
    private String refreshToken;
    private String imageUrl;

}
