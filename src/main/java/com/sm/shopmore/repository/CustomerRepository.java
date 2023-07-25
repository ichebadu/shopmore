package com.sm.shopmore.repository;

import com.sm.shopmore.entity.customer.CustomerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerUser, Long> {
    Optional<CustomerUser> findByEmail(String email);

    boolean existsByEmail(String email);
}
