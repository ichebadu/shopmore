package com.sm.shopmore.controller;

import com.sm.shopmore.dto.request.LoginRequest;
import com.sm.shopmore.dto.request.OTPVerificationRequest;
import com.sm.shopmore.dto.response.LoginResponse;
import com.sm.shopmore.dto.response.RegistrationResponse;
import com.sm.shopmore.dto.response.ResponseOtp;
import com.sm.shopmore.dto.response.auth.ApiResponse;
import com.sm.shopmore.dto.request.CustomerRequest.CustomerRegistrationRequest;
import com.sm.shopmore.entity.customer.CustomerUser;
import com.sm.shopmore.service.CustomerService;
import com.sm.shopmore.service.LoginAuthenticationService;


import com.sm.shopmore.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/customer")
@Slf4j
public class AuthCustomerController {
    private final LoginAuthenticationService loginAuthenticationService;
    private final CustomerService customerService;
    private final OtpService<CustomerUser> otpService;

    @PostMapping("/register" )
    public ResponseEntity<ApiResponse<RegistrationResponse>> register(@RequestBody CustomerRegistrationRequest request){
        ApiResponse<RegistrationResponse> apiResponse = new ApiResponse<>(customerService.registrationResponse(request));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping("/verify_user")
    public ResponseEntity<ApiResponse<ResponseOtp>> otpVerification(@RequestBody OTPVerificationRequest request){
        ApiResponse<ResponseOtp> apiResponse = new ApiResponse<>(otpService.verifyUserOtp(request.getEmail(), request.getOtp()));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> customerLogin(@RequestBody LoginRequest request){
        ApiResponse<LoginResponse> apiResponse = new ApiResponse<>(loginAuthenticationService.loginAuthentication(request));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
