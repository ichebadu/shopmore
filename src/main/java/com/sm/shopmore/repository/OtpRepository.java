package com.sm.shopmore.repository;

import com.sm.shopmore.entity.admin.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Otp findByUser_EmailAndOtp(String email, String otp);
    Otp findByUserId(Long id);
}
