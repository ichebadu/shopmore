package com.sm.shopmore.service.implementation;

import com.sm.shopmore.dto.request.CustomerRequest.CustomerRegistrationRequest;
import com.sm.shopmore.dto.response.RegistrationResponse;
import com.sm.shopmore.entity.RoleEntity;
import com.sm.shopmore.entity.admin.Otp;
import com.sm.shopmore.entity.customer.CustomerUser;
import com.sm.shopmore.events.registrationNotification.UserRegistrationEvent;
import com.sm.shopmore.repository.CustomerRepository;
import com.sm.shopmore.service.CustomerService;
import com.sm.shopmore.service.OtpService;
import com.sm.shopmore.utils.MapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final MapperUtils mapperUtils;
    private final ApplicationEventPublisher publisher;
    private final OtpService<CustomerUser> otpService;
    @Override
    public RegistrationResponse registrationResponse(CustomerRegistrationRequest request) {
        CustomerUser customerUser = mapperUtils.CustomerRegistrationDtoToCustomerEntity(request);
        customerUser.setPassword(passwordEncoder.encode(request.getPassword()));
        customerRepository.save(customerUser);

        log.info("CUSTOMER SAVED IN DATA BASE" + customerRepository.findByEmail(request.getEmail()));

        Otp otpEntity = otpService.generateOtp(customerUser);
        otpEntity.setUser(customerUser);
        otpService.saveOtp(otpEntity);
        String otp = otpEntity.getOtp();
        publisher.publishEvent(new UserRegistrationEvent(customerUser,otp));

        return RegistrationResponse.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .message("Customer Registration Successful")
                .build();
    }
}
