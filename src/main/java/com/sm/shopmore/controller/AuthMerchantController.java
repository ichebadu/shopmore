package com.sm.shopmore.controller;

import com.sm.shopmore.dto.request.LoginRequest;
import com.sm.shopmore.dto.response.LoginResponse;
import com.sm.shopmore.dto.response.RegistrationResponse;
import com.sm.shopmore.dto.response.auth.ApiResponse;
import com.sm.shopmore.dto.request.merchantRequest.MerchantRegisterRequest;
import com.sm.shopmore.service.LoginAuthenticationService;
import com.sm.shopmore.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/merchant")
@RequiredArgsConstructor
public class AuthMerchantController {
    private final MerchantService merchantService;
    private final LoginAuthenticationService loginAuthenticationService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegistrationResponse>> merchantRegistration(@RequestBody MerchantRegisterRequest request){
        ApiResponse<RegistrationResponse> apiResponse = new ApiResponse<>(merchantService.registrationResponse(request));
        return new ResponseEntity (apiResponse, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> merchantAuthenticate(@RequestBody LoginRequest request){
        ApiResponse<LoginResponse> apiResponse = new ApiResponse<>(loginAuthenticationService.loginAuthentication(request));
        return new ResponseEntity (apiResponse, HttpStatus.OK);
    }
}
