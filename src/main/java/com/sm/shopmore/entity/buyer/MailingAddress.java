package com.sm.shopmore.entity.buyer;


import com.sm.shopmore.enums.City;
import com.sm.shopmore.enums.RegionApi;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailingAddress {
    @Id
    @GeneratedValue
    private Long id;
    private String deliveryAddress;
    private String AdditionalInformation;
    private String AdditionPhoneNumber;
    @Enumerated(EnumType.STRING)
    private City city;
    @Enumerated(EnumType.STRING)
    private RegionApi region;

}
