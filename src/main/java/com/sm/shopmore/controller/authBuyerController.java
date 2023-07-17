package com.sm.shopmore.controller;

import com.sm.shopmore.auth.AuthenticationRequest;
import com.sm.shopmore.auth.AuthenticationResponse;
import com.sm.shopmore.auth.AuthenticationService;
import com.sm.shopmore.dto.request.BuyersRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class authBuyerController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register/seller" )
    public ResponseEntity<AuthenticationResponse> register(@RequestBody BuyersRegisterRequest request){
        return ResponseEntity.ok(authenticationService.BuyerRegistration(request));
    }

    @PostMapping("/register/seller/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.buyerAuthentication(request));
    }
}
