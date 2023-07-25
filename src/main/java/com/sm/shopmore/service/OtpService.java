package com.sm.shopmore.service;

import com.sm.shopmore.dto.response.ResponseOtp;
import com.sm.shopmore.entity.User;
import com.sm.shopmore.entity.admin.Otp;

public interface OtpService <T extends User>{
    ResponseOtp verifyUserOtp(String email, String otp);
    void saveOtp(Otp otp);
    ResponseOtp resendOtp(String email);
    void sendOtp(T user, String otp, Otp newOtp);
    Otp generateOtp(User user);
}
