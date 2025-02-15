package com.myproject.cricketlivescore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
public class MatchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Match match;

    private String winnerTeam;
    private int totalRuns;
    private int totalWickets;
    private String playerOfTheMatch;

    @CreationTimestamp
    private LocalDateTime matchDate;


}

