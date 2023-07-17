package com.sm.shopmore.entity.buyer;

import com.sm.shopmore.entity.User;
import com.sm.shopmore.entity.Wallet;
import com.sm.shopmore.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BuyerProfile extends User {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dateOfBirth;
    private String phone;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="wallet",referencedColumnName = "id")
    private Set<Wallet> wallet;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
