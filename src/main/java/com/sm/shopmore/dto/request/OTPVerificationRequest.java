package com.sm.shopmore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OTPVerificationRequest {
    private String email;

    private String otp;
}
