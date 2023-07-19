package com.sm.shopmore.entity.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="products")
public class Product {
    @Id
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name="price", nullable = false)
    private String price;
    @Column(name="quantity", nullable = false)
    private String quantity;
    @Column(name="productName", nullable = false)
    private String productName;
    private LocalDateTime createdOn;
    private LocalDateTime updated;
    @Column(name="description", nullable = false)
    private String Description;
}
