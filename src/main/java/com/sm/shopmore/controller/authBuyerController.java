package com.sm.shopmore.controller;

import com.sm.shopmore.auth.AuthenticationRequest;
import com.sm.shopmore.auth.AuthenticationResponse;
import com.sm.shopmore.auth.AuthenticationService;
import com.sm.shopmore.dto.request.BuyerRegisterRequest;
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
    public ResponseEntity<AuthenticationResponse> register(@RequestBody BuyerRegisterRequest request){
        return ResponseEntity.ok(authenticationService.buyerRegistration(request));
    }

    @PostMapping("/register/seller/login")
    public ResponseEntity<AuthenticationResponse> buyerLogin(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.buyerAuthentication(request));
    }
}
