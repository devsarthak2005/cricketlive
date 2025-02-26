package com.myproject.cricketlivescore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchRequest {
    private String teamA;
    private String teamB;
    private String matchDate;
    private String venue;
}
