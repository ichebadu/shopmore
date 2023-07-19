package com.sm.shopmore.entity.store;

import com.sm.shopmore.entity.payment.Wallet;
import com.sm.shopmore.enums.ModeOfPayment;
import com.sm.shopmore.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    @Column(nullable = false)
    private String Amount;
    @Column(nullable = false)
    private String reference;
    @Column(nullable = false)
    private String paymentPurpose;
    @Enumerated(EnumType.STRING)
    private ModeOfPayment modeOfPayment;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "transaction_id", nullable = false)
    private Wallet wallet;
    @Column(nullable = false)
    private String cost;


}
