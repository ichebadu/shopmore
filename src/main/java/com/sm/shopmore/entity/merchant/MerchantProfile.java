package com.sm.shopmore.entity.merchant;

import com.sm.shopmore.entity.User;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MerchantProfile extends User {
    private String imageUrl;
    private String CAC;
}
