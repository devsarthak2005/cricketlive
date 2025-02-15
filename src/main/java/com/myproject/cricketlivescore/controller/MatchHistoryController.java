package com.myproject.cricketlivescore.controller;

import com.myproject.cricketlivescore.model.MatchHistory;
import com.myproject.cricketlivescore.service.MatchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/match-history")
public class MatchHistoryController {
    @Autowired
    private MatchHistoryService matchHistoryService;

    @GetMapping("/{tournamentId}")
    public ResponseEntity<List<MatchHistory>> getMatchHistory(@PathVariable Long tournamentId) {
        List<MatchHistory> history = matchHistoryService.getMatchHistory(tournamentId);
        return ResponseEntity.ok(history);
    }
}
