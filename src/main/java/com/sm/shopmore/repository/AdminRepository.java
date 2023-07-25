package com.sm.shopmore.repository;

import com.sm.shopmore.entity.admin.AdminUser;

import java.util.Optional;

public interface AdminRepository {
    Optional<AdminUser> findAdminUserByEmail(String email);
    boolean existsByEmail(String email);
}

