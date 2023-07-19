package com.sm.shopmore.entity.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sm.shopmore.entity.merchant.MerchantUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cart_List")
public class CartList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
     private String Id;
    @Column(nullable = false)
    private String ProductName;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private Integer orderQuantity;
    @Column(nullable = false)
    private Double unitePrice;
    @Column(nullable = false)
    private Double subTotal;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private MerchantUser user;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Cart cart;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id",nullable = false)
    private Product product;

}
