package com.sm.shopmore.service.implementation;

import com.sm.shopmore.dto.request.merchantRequest.MerchantRegisterRequest;
import com.sm.shopmore.dto.response.RegistrationResponse;
import com.sm.shopmore.entity.admin.Otp;
import com.sm.shopmore.entity.merchant.MerchantUser;
import com.sm.shopmore.events.registrationNotification.UserRegistrationEvent;
import com.sm.shopmore.repository.MerchantRepository;
import com.sm.shopmore.service.MerchantService;
import com.sm.shopmore.service.OtpService;
import com.sm.shopmore.utils.MapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {
    private final MapperUtils mapperUtils;
    private final PasswordEncoder passwordEncoder;
    private final MerchantRepository merchantRepository;
    private final OtpService<MerchantUser> otpService;
    private final ApplicationEventPublisher publisher;


    @Override
    public RegistrationResponse registrationResponse(MerchantRegisterRequest request) {
        MerchantUser merchantUser = mapperUtils.merchantRegistrationDtoToMerchantEntity(request);
        merchantUser.setPassword(passwordEncoder.encode(request.getPassword()));
        merchantRepository.save(merchantUser);

        Otp otpEntity = otpService.generateOtp(merchantUser);
        otpEntity.setUser(merchantUser);
        otpService.saveOtp(otpEntity);
        String otp = otpEntity.getOtp();
        publisher.publishEvent(new UserRegistrationEvent(merchantUser,otp));

        return RegistrationResponse.builder()
                .firstName(merchantUser.getFirstName())
                .lastName(merchantUser.getLastName())
                .message("Merchant Registration Successful")
                .build();
    }
}
