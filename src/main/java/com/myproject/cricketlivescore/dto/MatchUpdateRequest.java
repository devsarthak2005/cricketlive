package com.myproject.cricketlivescore.dto;

import lombok.Data;

@Data
public class MatchUpdateRequest {
    private Long matchId;
    private String status;
    private String score;
}
