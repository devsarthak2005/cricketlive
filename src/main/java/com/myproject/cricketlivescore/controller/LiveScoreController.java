package com.myproject.cricketlivescore.controller;

import com.myproject.cricketlivescore.dto.LiveScoreUpdate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LiveScoreController {

    @MessageMapping("/update-score")
    @SendTo("/live-score/match")
    public LiveScoreUpdate sendLiveScore(LiveScoreUpdate scoreUpdate) {
        return scoreUpdate;
    }
}
