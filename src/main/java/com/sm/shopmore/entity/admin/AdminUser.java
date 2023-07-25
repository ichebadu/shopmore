package com.sm.shopmore.entity.admin;

import com.sm.shopmore.entity.User;
import com.sm.shopmore.enums.Country;
import com.sm.shopmore.enums.RegionApi;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminUser extends User implements Serializable {


    @Column(name = "department")
    private String department;


    @NotNull
    @NotEmpty(message = "Enter your street address")
    @Size(min = 5, max = 50)
    private String streetAddress;


    @Column(name = "image_url")
    private String imageURL;


    @NotNull
    @NotEmpty(message = "Enter your mobile number")
    @Size(min = 11, max = 20, message = "Mobile number must be 11 to 15 characters long")
    private String phoneNumber;


    @Column(name="dateOfBirth", nullable = false)
    private Date dateOfBirth;

    @NotNull
    @NotEmpty(message = "Enter your city")
    @Size(min = 5, max = 50)
    private String city;
    @Enumerated(EnumType.STRING)
    private Country country;
    @Enumerated(EnumType.STRING)
    private RegionApi regionApi;



}
