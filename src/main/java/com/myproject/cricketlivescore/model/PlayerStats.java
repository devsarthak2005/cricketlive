package com.myproject.cricketlivescore.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    private int matchesPlayed;
    private int totalRuns;
    private int totalBoundaries;
    private int totalWickets;
    private int totalCatches;
    private int halfCenturies;
    private int centuries;
    private double strikeRate;
    private double average;
    private int totalPoints;

    public void updateFantasyPoints() {
        this.totalPoints = (totalRuns * 1) + (totalBoundaries * 2) + (totalWickets * 10) + (totalCatches * 5);
    }
}
