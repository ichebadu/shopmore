package com.sm.shopmore.service;

import com.sm.shopmore.dto.request.LoginRequest;
import com.sm.shopmore.dto.response.LoginResponse;

public interface LoginAuthenticationService {
    LoginResponse loginAuthentication(LoginRequest login);
}
