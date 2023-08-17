package com.sm.shopmore.service.implementation;

import com.sm.shopmore.dto.request.ResetPasswordRequest;
import com.sm.shopmore.dto.response.ResponseOtp;
import com.sm.shopmore.entity.User;
import com.sm.shopmore.entity.admin.AdminUser;
import com.sm.shopmore.entity.ConfirmationToken;
import com.sm.shopmore.entity.customer.CustomerUser;
import com.sm.shopmore.entity.merchant.MerchantUser;
import com.sm.shopmore.events.password.UserForgotPasswordEvent;
import com.sm.shopmore.events.registrationNotification.UserRegistrationEvent;
import com.sm.shopmore.exception.OtpException;
import com.sm.shopmore.exception.SamePasswordException;
import com.sm.shopmore.exception.UserNotFoundException;
import com.sm.shopmore.repository.AdminRepository;
import com.sm.shopmore.repository.CustomerRepository;
import com.sm.shopmore.repository.MerchantRepository;
import com.sm.shopmore.repository.OtpRepository;
import com.sm.shopmore.service.ConfirmationService;
import com.sm.shopmore.utils.EmailUtils;
import com.sm.shopmore.utils.RandomGeneratedValue;
import com.sm.shopmore.utils.VerifyUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Slf4j
public class ConfirmationServiceImpl<T extends User> implements ConfirmationService<T> {


    private final CustomerRepository customerRepository;
    private final MerchantRepository merchantRepository;
    private final AdminRepository adminRepository;
    private final VerifyUser verifyUser;
    private final OtpRepository otpRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseOtp verifyUserOtp(String email, String otp) {
        User user = verifyUser.verifyingUser(email);

        log.info("Verifying OTP:: " + user.getEmail());
        ConfirmationToken confirmationTokenConfirmation = otpRepository.findByUser_EmailAndOtp(user.getEmail(), otp);
        System.out.println(confirmationTokenConfirmation);

        if(confirmationTokenConfirmation !=null && !isOtpExpired(confirmationTokenConfirmation)) {
            log.info(confirmationTokenConfirmation.getUser().toString());
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


    public void sendOtp(User user, String otp, ConfirmationToken newConfirmationToken){
        ConfirmationToken foundConfirmationToken = otpRepository.findByUserId(user.getId());

        if(foundConfirmationToken != null){
            otpRepository.delete(foundConfirmationToken);
        }
        otpRepository.save(newConfirmationToken);
        log.info(otp);
        applicationEventPublisher.publishEvent(new UserRegistrationEvent(user,otp));
    }

    @Override
    public void saveOtp(ConfirmationToken confirmationToken) {
        otpRepository.save(confirmationToken);
    }

    @Override
    public ResponseOtp resendOtp(String email){
        User user;
        try {
            user = verifyUser.verifyingUser(email);

            String generateOtp = RandomGeneratedValue.generateRandomValues();
            ConfirmationToken newConfirmationToken = generateOtp(user);

            sendOtp(user, generateOtp, newConfirmationToken);

            return ResponseOtp.builder()
                    .message(generateOtp)
                    .localDateTime(LocalDateTime.now())
                    .build();
        } catch (Exception e){
            log.info("Error resending OTP: ", e.getMessage());
            throw new OtpException("Error resending otp");
        }
    }

    public boolean isOtpExpired(ConfirmationToken confirmationToken){
        LocalDateTime otpCreatedAt = confirmationToken.getOtpExpiresAt();
        LocalDateTime currentDateTime = LocalDateTime.now();
        Duration duration = Duration.between(otpCreatedAt, currentDateTime);
        long minutesPassed = duration.toMinutes();
        long otpExpiresAt = 4;
        return minutesPassed > otpExpiresAt;
    }
    public ConfirmationToken generateOtp(User user){
        String otp = RandomGeneratedValue.generateRandomValues();

        ConfirmationToken confirmationTokenEntity;

        if(user instanceof CustomerUser customerUser){
            confirmationTokenEntity = new ConfirmationToken(otp, customerUser);
        } else if(user instanceof  MerchantUser merchantUser){
            confirmationTokenEntity = new ConfirmationToken(otp,merchantUser);
        }
        else if(user instanceof  AdminUser adminUser){
            confirmationTokenEntity = new ConfirmationToken(otp,adminUser);
        } else {
            throw new IllegalArgumentException("Invalid user");
        }
        return confirmationTokenEntity;
    }

    @Override
    public String verifyToken(String token){
        ConfirmationToken confirmationConfirmationToken = otpRepository.findByOtp(token)
                .orElseThrow(()-> new UserNotFoundException("Invalid Credential"));
        if(isOtpExpired(confirmationConfirmationToken)){
            otpRepository.delete(confirmationConfirmationToken);
            throw new OtpException("OTP is Expired");
        }

        return confirmationConfirmationToken.getUser().getEmail();
    }

    @Override
    public String forgotPassword(String email, HttpServletRequest request) {
        User user = verifyUser.verifyingUser(email);
        ConfirmationToken confirmationToken = otpRepository.findAllByUser(user);
        if(confirmationToken!= null){
            otpRepository.delete(confirmationToken);
        }
        applicationEventPublisher.publishEvent(new UserForgotPasswordEvent(user, EmailUtils.applicationUrl(request)));
        return "Please Check Your Mail For Password Reset Link";
    }
    @Override
    public String resetPassword(ResetPasswordRequest resetPasswordRequest, String token){
        String email = verifyToken(token);
        User user = verifyUser.verifyingUser(email);
        if(passwordEncoder.matches(resetPasswordRequest.getPassword(), user.getPassword())) {
            throw new SamePasswordException("Please choose a different password");
        }

        user.setPassword(passwordEncoder.encode(resetPasswordRequest.getPassword()));

        if (user instanceof CustomerUser) {
            customerRepository.save((CustomerUser) user);
        } else if (user instanceof AdminUser) {
            adminRepository.save((AdminUser) user);
        } else if (user instanceof MerchantUser) {
            merchantRepository.save((MerchantUser) user);
        } else {
            throw new IllegalArgumentException("Unsupported user type");
        }

        return "Password changed successfully";
    }


}
