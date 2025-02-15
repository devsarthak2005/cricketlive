package com.myproject.cricketlivescore.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    private int totalRuns;
    private int totalWickets;
    private int totalCatches;
    private int totalBoundaries;
    private int runs;
    private int wickets;
    private int catches;
    private int boundaries;
    private int sixes;
    private int fantasyPoints;

    public void updateFantasyPoints() {
        this.fantasyPoints = (totalRuns * 1) + (totalBoundaries * 2) + (totalWickets * 30) + (totalCatches * 10);
    }
}
