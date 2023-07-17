package com.sm.shopmore.entity;


import com.sm.shopmore.enums.City;
import com.sm.shopmore.enums.RegionApi;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class MailingAddress extends User {
    private String deliveryAddress;
    private String AdditionalInformation;
    private String AdditionPhoneNumber;
    @Enumerated(EnumType.STRING)
    private City city;
    @Enumerated(EnumType.STRING)
    private RegionApi region;

}
