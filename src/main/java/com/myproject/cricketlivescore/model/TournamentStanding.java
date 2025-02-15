package com.myproject.cricketlivescore.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class TournamentStanding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tournament tournament;

    private String teamName;
    private int matchesPlayed;
    private int wins;
    private int losses;
    private int points;


}
