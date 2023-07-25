package com.sm.shopmore.entity.store.utils;

import com.sm.shopmore.entity.buyer.BuyerUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="buyer_id", nullable = false)
    private BuyerUser buyerUser;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String comment;
    @Column(nullable = false)
    private int rating;

}
