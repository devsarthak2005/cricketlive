package com.myproject.cricketlivescore.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreUpdateMessage {
    private Long playerId;
    private int runs;
    private int wickets;
    private int catches;
    private int boundaries;
    private int sixes;
}
