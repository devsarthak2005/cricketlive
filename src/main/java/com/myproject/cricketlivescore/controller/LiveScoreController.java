package com.myproject.cricketlivescore.controller;

import com.myproject.cricketlivescore.dto.LiveScoreDTO;
import com.myproject.cricketlivescore.dto.LiveScoreUpdate;
import com.myproject.cricketlivescore.service.MatchService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class LiveScoreController
{

    private final SimpMessagingTemplate messagingTemplate;
    private final MatchService matchService;

    public LiveScoreController(SimpMessagingTemplate messagingTemplate, MatchService matchService) {
        this.messagingTemplate = messagingTemplate;
        this.matchService = matchService;
    }



    @MessageMapping("/update-score")
    public void updateScore(LiveScoreDTO liveScore) {
        matchService.updateMatchScore(liveScore.getMatchId(), liveScore.getScoreTeamA(), liveScore.getScoreTeamB());

        // Broadcasting updated score to all subscribers
        messagingTemplate.convertAndSend("/topic/match", liveScore);
    }
}
