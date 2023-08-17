package com.sm.shopmore.service;

import com.sm.shopmore.dto.request.ResetPasswordRequest;
import com.sm.shopmore.dto.response.ResponseOtp;
import com.sm.shopmore.entity.User;
import com.sm.shopmore.entity.ConfirmationToken;
import com.sm.shopmore.exception.SamePasswordException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

public interface ConfirmationService<T extends User>{
    ResponseOtp verifyUserOtp(String email, String otp);
    void saveOtp(ConfirmationToken confirmationToken);
    ResponseOtp resendOtp(String email);
    void sendOtp(T user, String otp, ConfirmationToken newConfirmationToken);
    ConfirmationToken generateOtp(User user);

    String verifyToken(String token);

    String forgotPassword(String email, HttpServletRequest request);



    String resetPassword(ResetPasswordRequest resetPasswordRequest, String token);
}
