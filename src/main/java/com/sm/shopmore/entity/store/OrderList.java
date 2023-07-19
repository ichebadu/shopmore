package com.sm.shopmore.entity.store;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="order_list")
public class OrderList {

    @Id
    @GeneratedValue
    private Long Id;

    @Column(name="product_name")
    private String productName;
    private int quantity;
    private Double unitPrice;
    private Double subTotal;
    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    private Order order;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
