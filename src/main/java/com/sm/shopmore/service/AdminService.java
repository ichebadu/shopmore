package com.sm.shopmore.service;

import com.sm.shopmore.dto.request.admin.AdminRegistrationRequest;
import com.sm.shopmore.dto.response.RegistrationResponse;

public interface AdminService {
    RegistrationResponse registration(AdminRegistrationRequest request);
}
