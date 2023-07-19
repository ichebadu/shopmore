package com.sm.shopmore.entity.store;

import com.sm.shopmore.enums.CategoryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(name="category_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category", orphanRemoval = true)
    private Set<Product> product;
}
