package com.sm.shopmore.repository;

import com.sm.shopmore.entity.ConfirmationToken;
import com.sm.shopmore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken findByUser_EmailAndOtp(String email, String otp);
    ConfirmationToken findByUserId(Long id);
    Optional<ConfirmationToken> findByOtp(String token);
    ConfirmationToken findAllByUser(User user);
}
