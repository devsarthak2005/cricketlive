package com.myproject.cricketlivescore.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    private int team1Score;
    private int team2Score;
    private int team1Wickets;
    private int team2Wickets;
    private int overs;
    private LocalDateTime updatedAt;
}
