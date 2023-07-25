package com.sm.shopmore.entity.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sm.shopmore.entity.buyer.BuyerUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="buyer_id")
    private BuyerUser buyer;
    @JsonIgnore
    @OneToMany(mappedBy = "cart",orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CartList> cartList = new HashSet<>();
    @Column(nullable = false)
    private Double totalAmount = 0.0;
}
