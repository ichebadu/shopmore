package com.sm.shopmore.auth;

import com.sm.shopmore.dto.request.BuyerRegisterRequest;
import com.sm.shopmore.dto.request.MerchantRegisterRequest;
import com.sm.shopmore.entity.User;
import com.sm.shopmore.enums.UserType;
import com.sm.shopmore.repository.UserRepository;
import com.sm.shopmore.security.JwtService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse buyerRegistration(BuyerRegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userType(UserType.SELLER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return
                AuthenticationResponse.builder()
                        .token(jwtToken)
                        .build();
    }

    public AuthenticationResponse buyerAuthentication(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("user not authenticated"));
        var jwtToken = jwtService.generateToken(user);
        System.out.println(user);
        return
                AuthenticationResponse.builder()
                        .token(jwtToken)
                        .build();
    }

    public AuthenticationResponse merchantRegistration(MerchantRegisterRequest request) {
        System.out.println(request);
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userType(UserType.MERCHANT)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return
                AuthenticationResponse.builder()
                        .token(jwtToken)
                        .build();
    }

    public AuthenticationResponse merchantAuthentication(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("user not authenticated"));
        System.out.println(user);
        var jwtToken = jwtService.generateToken(user);
        return
                AuthenticationResponse.builder()
                        .token(jwtToken)
                        .build();
    }
}
