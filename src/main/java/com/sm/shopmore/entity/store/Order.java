package com.sm.shopmore.entity.store;

import com.sm.shopmore.entity.buyer.BuyerUser;
import com.sm.shopmore.entity.buyer.MailingAddress;
import com.sm.shopmore.enums.DeliveryStatus;
import com.sm.shopmore.enums.ModeOfPayment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable =false)
    private int quantity;
    @CreationTimestamp
    private LocalDateTime orderCreatedOn;
    @UpdateTimestamp
    private LocalDateTime orderUpdatedOn;
    @Enumerated(EnumType.STRING)
    private ModeOfPayment modeOfPayment;
    @Column(nullable = false)
    private Double grandTotal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderList> orderList = new HashSet<>();
    @Enumerated
    @OneToOne(cascade = CascadeType.ALL)
    private MailingAddress mailingAddress;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="buyer_id", nullable = false)
    private BuyerUser buyerUser;

    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name= "transaction_id")
    private Transaction transaction;

    @Column(name = "delivery_fee")
    private Double deliveryFee;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

}