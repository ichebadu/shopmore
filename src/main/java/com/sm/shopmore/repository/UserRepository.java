package com.sm.shopmore.repository;

import com.sm.shopmore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    Optional<org.springframework.security.core.userdetails.User> findByEmail(String email);
}
