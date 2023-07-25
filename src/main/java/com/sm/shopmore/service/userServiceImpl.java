package com.sm.shopmore.service;

import com.sm.shopmore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class userServiceImpl {
//    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

}
