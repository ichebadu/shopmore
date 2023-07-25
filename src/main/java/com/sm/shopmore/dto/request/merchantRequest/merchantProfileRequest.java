package com.sm.shopmore.dto.request.merchantRequest;

import com.sm.shopmore.enums.BusinessType;
import jakarta.persistence.*;

public class merchantProfileRequest {

    private String shopName;
    @Enumerated(EnumType.STRING)
    private BusinessType businessType;
    @Column(name ="AccountManagersFirstName")
    private String FirstName;
    @Column(name ="AccountManagersLastName")
    private String LastName;
    @Column(name ="AccountManagersPhoneNumber")
    private String AccountManagersPhoneNumber;
    private String email;
    private String password;
}
