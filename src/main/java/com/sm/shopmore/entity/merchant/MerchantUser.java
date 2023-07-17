package com.sm.shopmore.entity.merchant;

import com.sm.shopmore.entity.User;
import com.sm.shopmore.enums.BusinessType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="merchant_user")
public class MerchantUser extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
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
