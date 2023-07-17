package com.sm.shopmore.entity.store;

import com.sm.shopmore.entity.buyer.BuyerProfile;
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

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="order")
public class Order {
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    @Column
    private int uid;
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

    @OneToOne(cascade = CascadeType.ALL)
    private MailingAddress mailingAddress;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id", nullable = false)
    private BuyerProfile buyerProfile;

    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name= "transaction_id")
    private Transaction transaction;

    @Column(name = "delivery_fee")
    private Double deliveryFee;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

}