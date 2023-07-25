package com.sm.shopmore.service.implementation;

import com.sm.shopmore.dto.request.admin.AdminRegistrationRequest;
import com.sm.shopmore.dto.response.RegistrationResponse;
import com.sm.shopmore.entity.admin.AdminUser;
import com.sm.shopmore.entity.admin.Otp;
import com.sm.shopmore.events.registrationNotification.UserRegistrationEvent;
import com.sm.shopmore.repository.AdminRepository;
import com.sm.shopmore.service.AdminService;
import com.sm.shopmore.service.OtpService;
import com.sm.shopmore.utils.MapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@RequiredArgsConstructor
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final MapperUtils mapperUtils;
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;
    private final OtpService<AdminUser> otpService;
    private final ApplicationEventPublisher publisher;



    public RegistrationResponse registration(AdminRegistrationRequest request){
        AdminUser adminUser = mapperUtils.adminUserDtoToAdminEntity(request);
        adminUser.setPassword(passwordEncoder.encode(request.getPassword()));
        var savedUser = adminRepository.save(adminUser);

        Otp otpEntity = otpService.generateOtp(savedUser);
        otpEntity.setUser(adminUser);
        otpService.saveOtp(otpEntity);
        String otp = otpEntity.getOtp();
        publisher.publishEvent(new UserRegistrationEvent(adminUser,otp));



        return RegistrationResponse.builder()
                .firstName(adminUser.getFirstName())
                .lastName(adminUser.getLastName())
                .message("Admin Registration Successful")
                .build();

    }
}
