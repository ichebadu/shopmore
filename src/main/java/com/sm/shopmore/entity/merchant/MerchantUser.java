package com.sm.shopmore.entity.merchant;

import com.sm.shopmore.entity.User;
import com.sm.shopmore.enums.BusinessType;
import com.sm.shopmore.enums.City;
import com.sm.shopmore.enums.Country;
import com.sm.shopmore.enums.DoYouHaveAShop;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantUser extends User implements Serializable {
    private Date dateOfBirth;
    private String shopName;
    @Enumerated(EnumType.STRING)
    private BusinessType businessType;
    @Column(name ="AccountManagersFirstName")
    private String firstName;
    @Column(name ="AccountManagersLastName")
    private String lastName;
    @Column(name ="AccountManagersPhoneNumber")
    private String AccountManagersPhoneNumber;
    @Enumerated(EnumType.STRING)
    private DoYouHaveAShop doYouHaveAShop;
    private String companyName;
    private String address1;
    private String address2;
    private String PostalCode;
    @Enumerated(EnumType.STRING)
    private City city;
    @Enumerated(EnumType.STRING)
    private Country country;
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
