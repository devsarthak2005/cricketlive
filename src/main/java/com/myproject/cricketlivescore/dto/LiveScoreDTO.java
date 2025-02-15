package com.myproject.cricketlivescore.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiveScoreDTO {
    private Long matchId;
    private int scoreTeamA;
    private int scoreTeamB;
}