package com.sm.shopmore.repository;

import com.sm.shopmore.entity.buyer.BuyerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuyerRepository extends JpaRepository<BuyerUser, Long> {
    Optional<BuyerUser> findBuyerUserByEmail (String email);
}
