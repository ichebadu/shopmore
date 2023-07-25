package com.sm.shopmore.service.implementation;

import com.sm.shopmore.dto.response.ResponseOtp;
import com.sm.shopmore.entity.User;
import com.sm.shopmore.entity.admin.AdminUser;
import com.sm.shopmore.entity.admin.Otp;
import com.sm.shopmore.entity.customer.CustomerUser;
import com.sm.shopmore.entity.merchant.MerchantUser;
import com.sm.shopmore.events.registrationNotification.UserRegistrationEvent;
import com.sm.shopmore.exception.OtpException;
import com.sm.shopmore.repository.AdminRepository;
import com.sm.shopmore.repository.CustomerRepository;
import com.sm.shopmore.repository.MerchantRepository;
import com.sm.shopmore.repository.OtpRepository;
import com.sm.shopmore.service.OtpService;
import com.sm.shopmore.utils.RandomGeneratedValue;
import com.sm.shopmore.utils.VerifyUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Slf4j
public class OtpServiceImpl<T extends User> implements OtpService<T> {


    private final CustomerRepository customerRepository;
    private final MerchantRepository merchantRepository;
    private final AdminRepository adminRepository;
    private final VerifyUser verifyUser;
    private final OtpRepository otpRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public ResponseOtp verifyUserOtp(String email, String otp) {
        User user = verifyUser.verifyingUser(email);

        log.info("Verifying OTP:: " + user.getEmail());
        Otp otpConfirmation = otpRepository.findByUser_EmailAndOtp(user.getEmail(), otp);
        System.out.println(otpConfirmation);

        if(otpConfirmation !=null && !isOtpExpired(otpConfirmation)) {
            log.info(otpConfirmation.getUser().toString());
            user.setStatus(true);

            if( user instanceof CustomerUser customerUser){
                customerRepository.save(customerUser);
            } else if(user instanceof MerchantUser merchantUser){
                merchantRepository.save(merchantUser);
            }
            else{
                AdminUser adminUser = (AdminUser) user;
                adminRepository.save(adminUser);
            }
            return ResponseOtp.builder()
                    .message(user.getEmail())
                    .localDateTime(LocalDateTime.now())
                    .build();
        }else {

            return ResponseOtp.builder()
                    .message("Invalid or expired OTP")
                    .localDateTime(LocalDateTime.now())
                    .build();
        }
    }


    public void sendOtp(User user, String otp, Otp newOtp){
        Otp foundOtp = otpRepository.findByUserId(user.getId());

        if(foundOtp != null){
            otpRepository.delete(foundOtp);
        }
        otpRepository.save(newOtp);
        log.info(otp);
        applicationEventPublisher.publishEvent(new UserRegistrationEvent(user,otp));
    }

    @Override
    public void saveOtp(Otp otp) {
        otpRepository.save(otp);
    }

    @Override
    public ResponseOtp resendOtp(String email){
        User user;
        try {
            user = verifyUser.verifyingUser(email);

            String generateOtp = RandomGeneratedValue.generateRandomValues();
            Otp newOtp = generateOtp(user);

            sendOtp(user, generateOtp, newOtp);

            return ResponseOtp.builder()
                    .message(generateOtp)
                    .localDateTime(LocalDateTime.now())
                    .build();
        } catch (Exception e){
            log.info("Error resending OTP: ", e.getMessage());
            throw new OtpException("Error resending otp");
        }
    }

    public boolean isOtpExpired(Otp otp){
        LocalDateTime otpCreatedAt = otp.getOtpExpiresAt();
        LocalDateTime currentDateTime = LocalDateTime.now();
        Duration duration = Duration.between(otpCreatedAt, currentDateTime);
        long minutesPassed = duration.toMinutes();
        long otpExpiresAt = 4;
        return minutesPassed > otpExpiresAt;
    }
    public Otp generateOtp(User user){
        String otp = RandomGeneratedValue.generateRandomValues();

        Otp otpEntity;

        if(user instanceof CustomerUser customerUser){
            otpEntity = new Otp(otp, customerUser);
        } else if(user instanceof  MerchantUser merchantUser){
            otpEntity = new Otp(otp,merchantUser);
        }
        else if(user instanceof  AdminUser adminUser){
            otpEntity = new Otp(otp,adminUser);
        } else {
            throw new IllegalArgumentException("Invalid user");
        }
        return otpEntity;
    }
}
