package com.sm.shopmore.service;

import com.sm.shopmore.dto.request.merchantRequest.MerchantRegisterRequest;
import com.sm.shopmore.dto.response.RegistrationResponse;

public interface MerchantService {
     RegistrationResponse registrationResponse (MerchantRegisterRequest request);

}
