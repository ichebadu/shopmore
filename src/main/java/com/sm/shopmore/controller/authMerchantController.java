package com.sm.shopmore.controller;

import com.sm.shopmore.auth.AuthenticationRequest;
import com.sm.shopmore.auth.AuthenticationResponse;
import com.sm.shopmore.auth.AuthenticationService;
import com.sm.shopmore.dto.request.merchantRequest.MerchantRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class authMerchantController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register/merchant")
    public ResponseEntity<AuthenticationResponse> merchantRegistration(@RequestBody MerchantRegisterRequest request){
        return ResponseEntity.ok(authenticationService.merchantRegistration(request));
    }
    @PostMapping("/registration/merchant/authenticate")
    public ResponseEntity<AuthenticationResponse> merchantAuthenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.merchantAuthentication(request));
    }
}
