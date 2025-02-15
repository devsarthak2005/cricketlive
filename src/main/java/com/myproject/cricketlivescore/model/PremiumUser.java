package com.myproject.cricketlivescore.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class PremiumUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private String planType;
    private LocalDate subscriptionStart;
    private LocalDate subscriptionEnd;
    private boolean isActive;

}
