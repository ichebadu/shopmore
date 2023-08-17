package com.sm.shopmore.controller;

import com.sm.shopmore.dto.request.ResetPasswordRequest;
import com.sm.shopmore.dto.response.ResponseOtp;
import com.sm.shopmore.dto.response.auth.ApiResponse;
import com.sm.shopmore.service.ConfirmationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/otp")
public class AuthController {
    private final ConfirmationService confirmationService;

    @GetMapping("/resendOtp")
    private ResponseEntity<ApiResponse<ResponseOtp>> resend(@RequestParam("email") String email){
        ApiResponse<ResponseOtp> apiResponse = new ApiResponse<>(confirmationService.resendOtp(email));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }
    @PostMapping("/reset")
    public ResponseEntity<ApiResponse<String>> resetPassword(@RequestBody ResetPasswordRequest request){
        ApiResponse<String> apiResponse = new ApiResponse<>(confirmationService.resetPassword(request,request.getToken()));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/verify-password-token")
    public ResponseEntity<ApiResponse<String>> verifyToken(@RequestParam("token") String token){
        ApiResponse<String> apiResponse = new ApiResponse<>(confirmationService.verifyToken(token));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @GetMapping("/forget-password")
    public ResponseEntity<ApiResponse<String>> forgotPassword(@RequestParam("email") String email, HttpServletRequest request){
        ApiResponse<String> apiResponse = new ApiResponse<>(confirmationService.forgotPassword(email,request));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

}
