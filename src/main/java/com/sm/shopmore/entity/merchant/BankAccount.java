package com.sm.shopmore.entity.merchant;

import com.sm.shopmore.enums.City;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String companyName;
    private String address1;
    private String address2;
    private String PostalCode;
    private City city;
    private String LegalCountry;
    private String businessOwnerOrLegalRepresentativeFirstName;
    private String businessOwnerOrLegalRepresentativeLastName;
    private String businessOwnerOrLegalRepresentativeDateOfBirth;



}
