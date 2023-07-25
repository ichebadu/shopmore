package com.sm.shopmore.entity.merchant;

import com.sm.shopmore.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MerchantProfile {
    @Id
    @GeneratedValue
    private Long id;
    private String imageUrl;
    private String CAC;
}
