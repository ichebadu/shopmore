package com.sm.shopmore.dto.request.CustomerRequest;

import com.sm.shopmore.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegistrationRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String dateOfBirth;

    private String phone;
    private String gender;
}
