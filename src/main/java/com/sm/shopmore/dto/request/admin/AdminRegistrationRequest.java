package com.sm.shopmore.dto.request.admin;

import com.sm.shopmore.enums.Country;
import com.sm.shopmore.enums.RegionApi;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdminRegistrationRequest {
    @Column(name="first_name", nullable = false)
    private String firstName;
    @Column(name="last_name", nullable = false)
    private String lastName;
    @NotNull
    @NotEmpty(message = "Email address must not be empty")
    @Size(min = 5, max = 50)
    @Email(message = "Enter email address")
    private String email;

    @NotNull
    @NotEmpty(message = "Enter your password")
    private String password;

    @Column(name="last_name", nullable = false)
    private String employeeId;
    @Column(name = "image_url")
    private String imageURL;

    @Column(name="last_name", nullable = false)
    private String department;

    @Column(name="role", nullable = false)
    private String role;

    @NotNull
    @NotEmpty(message = "Enter your mobile number")
    @Size(min = 11, max = 20, message = "Mobile number must be 11 to 15 characters long")
    private String phoneNumber;

    @NotNull
    @NotEmpty(message = "Enter your street address")
    @Size(min = 5, max = 50)
    private String streetAddress;

    @Column(name="dateOfBirth", nullable = false)
    private String dateOfBirth;

    @NotNull
    @NotEmpty(message = "Enter your city")
    @Size(min = 5, max = 50)
    private String city;
    private Country country;
    @Enumerated(EnumType.STRING)
    private RegionApi regionApi;


}
