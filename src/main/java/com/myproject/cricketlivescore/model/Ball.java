package com.myproject.cricketlivescore.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ball {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;  // Link to the match

    @Column(nullable = false)
    private int overNumber;

    @Column(nullable = false)
    private int ballNumber;

    @ManyToOne
    @JoinColumn(name = "batsman_id", nullable = false)
    private Player batsman;  // Player on strike

    @ManyToOne
    @JoinColumn(name = "bowler_id", nullable = false)
    private Player bowler;  // Bowler for this ball

    @Column(nullable = false)
    private int runs;  // Runs scored

    private boolean isWide;
    private boolean isNoBall;
    private boolean isWicket;
}
