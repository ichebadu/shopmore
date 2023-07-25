package com.sm.shopmore.repository;

import com.sm.shopmore.entity.admin.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminUser,Long> {
    Optional<AdminUser> findByEmail(String email);
    boolean existsByEmail(String email);
}

