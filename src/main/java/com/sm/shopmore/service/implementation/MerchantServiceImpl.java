package com.sm.shopmore.service.implementation;

import com.sm.shopmore.dto.request.merchantRequest.MerchantRegisterRequest;
import com.sm.shopmore.dto.response.RegistrationResponse;
import com.sm.shopmore.entity.ConfirmationToken;
import com.sm.shopmore.entity.merchant.MerchantUser;
import com.sm.shopmore.events.registrationNotification.UserRegistrationEvent;
import com.sm.shopmore.repository.MerchantRepository;
import com.sm.shopmore.service.MerchantService;
import com.sm.shopmore.service.ConfirmationService;
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
    private final ConfirmationService<MerchantUser> confirmationService;
    private final ApplicationEventPublisher publisher;


    @Override
    public RegistrationResponse registration(MerchantRegisterRequest request) {
        MerchantUser merchantUser = mapperUtils.merchantRegistrationDtoToMerchantEntity(request);
        merchantUser.setPassword(passwordEncoder.encode(request.getPassword()));
        merchantRepository.save(merchantUser);

        ConfirmationToken confirmationTokenEntity = confirmationService.generateOtp(merchantUser);
        confirmationTokenEntity.setUser(merchantUser);
        confirmationService.saveOtp(confirmationTokenEntity);
        String otp = confirmationTokenEntity.getOtp();
        publisher.publishEvent(new UserRegistrationEvent(merchantUser,otp));

        return RegistrationResponse.builder()
                .firstName(merchantUser.getFirstName())
                .lastName(merchantUser.getLastName())
                .message("Merchant Registration Successful")
                .build();
    }
}
