package com.sm.shopmore.repository;

import com.sm.shopmore.entity.merchant.MerchantUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<MerchantUser, Long> {
    Optional<MerchantUser> findByEmail(String email);

    boolean existsByEmail(String email);
}
