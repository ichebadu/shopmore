package com.sm.shopmore.entity;

import com.sm.shopmore.enums.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Product {
    @Id
    private Long id;
    private String name;
    private Category category;
    private String price;
    private String quantity;
    private String imageName;
    private LocalDateTime createdOn;
    private LocalDateTime updated;
    private String Description;
}
