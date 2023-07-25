package com.sm.shopmore.entity.merchant;

import com.sm.shopmore.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;


@Entity
@Data
@Builder
public class MerchantUser extends User {
    public MerchantUser() {
    }
}
