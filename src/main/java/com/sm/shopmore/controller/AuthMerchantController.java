package com.sm.shopmore.controller;

import com.sm.shopmore.dto.request.LoginRequest;
import com.sm.shopmore.dto.request.OTPVerificationRequest;
import com.sm.shopmore.dto.request.merchantRequest.MerchantRegisterRequest;
import com.sm.shopmore.dto.response.LoginResponse;
import com.sm.shopmore.dto.response.RegistrationResponse;
import com.sm.shopmore.dto.response.ResponseOtp;
import com.sm.shopmore.dto.response.auth.ApiResponse;
import com.sm.shopmore.entity.merchant.MerchantUser;
import com.sm.shopmore.service.LoginAuthenticationService;
import com.sm.shopmore.service.MerchantService;
import com.sm.shopmore.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/merchant")
public class AuthMerchantController {

        private final MerchantService merchantService;
        private final OtpService<MerchantUser> otpService;
        private final LoginAuthenticationService loginAuthenticationService;

        @PostMapping("/register")
        public ResponseEntity<ApiResponse<RegistrationResponse>> AdminRegistration(@RequestBody MerchantRegisterRequest request){
            ApiResponse<RegistrationResponse> apiResponse = new ApiResponse<>(merchantService.registration(request));
            return new ResponseEntity (apiResponse, HttpStatus.OK);
        }
        @PostMapping("/verify_user")
        public ResponseEntity<ApiResponse<ResponseOtp>> otpVerification(@RequestBody OTPVerificationRequest request){
            ApiResponse<ResponseOtp> apiResponse = new ApiResponse<>(otpService.verifyUserOtp(request.getEmail(), request.getOtp()));
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);

        }
        @PostMapping("/login")
        public ResponseEntity<ApiResponse<LoginResponse>> AdminAuthenticate(@RequestBody LoginRequest request){
            ApiResponse<LoginResponse> apiResponse = new ApiResponse<>(loginAuthenticationService.loginAuthentication(request));
            return new ResponseEntity (apiResponse, HttpStatus.OK);
        }
}
