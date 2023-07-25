package com.sm.shopmore.dto.request.merchantRequest;

import com.sm.shopmore.entity.merchant.BankAccount;
import com.sm.shopmore.enums.BusinessType;
import com.sm.shopmore.enums.City;
import com.sm.shopmore.enums.DoYouHaveAShop;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantRegisterRequest {
    @Column(name="first_name", nullable = false)
    private String firstName;
    @Column(name="last_name", nullable = false)
    private String lastName;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="password", nullable = false)
    private String password;
    private Date dateOfBirth;
    @Column(name="phone", nullable = false)
    private String phone;
    private String shopName;
    @Enumerated(EnumType.STRING)
    private BusinessType businessType;
    @Column(name ="AccountManagersFirstName")
    private String FirstName;
    @Column(name ="AccountManagersLastName")
    private String LastName;
    @Column(name ="AccountManagersPhoneNumber")
    private String AccountManagersPhoneNumber;
    @Enumerated(EnumType.STRING)
    private DoYouHaveAShop doYouHaveAShop;
    private String companyName;
    private String address1;
    private String address2;
    private String PostalCode;
    private String city;
    private String Country;
    private String businessOwnerOrLegalRepresentativeFirstName;
    private String businessOwnerOrLegalRepresentativeLastName;
    private String businessOwnerOrLegalRepresentativeDateOfBirth;
    private String imageUrl;
    private String passportOrIdOfBusinessOwner;
    private String sizeOfBusiness;
    private String UploadCAC;
    private String UploadTIN;
    private String AreYouARegisteredVAT;
    private String CACRegistrationNumber;
    private String TaxIdentificationNumber;
    private String WhichCountryWillYouShipFrom;
}
