package com.myproject.cricketlivescore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiveScoreUpdate {
    private Long matchId;
    private String battingTeam;
    private int totalRuns;
    private int wickets;
    private float overs;
    private String lastBall;
}
