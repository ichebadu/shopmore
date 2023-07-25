package com.sm.shopmore.entity.customer;

import com.sm.shopmore.entity.User;
import com.sm.shopmore.entity.payment.Wallet;
import com.sm.shopmore.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerUser extends User implements Serializable {

    private Date dateOfBirth;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
