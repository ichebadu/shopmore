package com.sm.shopmore.service;

import com.sm.shopmore.dto.request.CustomerRequest.CustomerRegistrationRequest;
import com.sm.shopmore.dto.response.RegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    RegistrationResponse registrationResponse(CustomerRegistrationRequest request);
}
