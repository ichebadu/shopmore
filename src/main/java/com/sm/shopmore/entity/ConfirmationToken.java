package com.sm.shopmore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="confirmationToken")
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false, unique = true)
    private String otp;

    @Column(nullable = false)
    private LocalDateTime otpExpiresAt;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @JsonIgnoreProperties("otp")
    private User user;

    public ConfirmationToken(String otp, User user){
        this.otp = otp;
        this.user = user;
        this.otpExpiresAt = otpExpiresDate();
    }

    private LocalDateTime otpExpiresDate(){
        return LocalDateTime.now().plusMinutes(5);
    }
}
