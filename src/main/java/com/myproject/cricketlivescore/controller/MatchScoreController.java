package com.myproject.cricketlivescore.controller;

import com.myproject.cricketlivescore.model.MatchScore;
import com.myproject.cricketlivescore.service.MatchScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-scores")
@RequiredArgsConstructor
public class MatchScoreController {

    private final MatchScoreService matchScoreService;

    @PostMapping("/update")
    public ResponseEntity<MatchScore> updateScore(@RequestBody MatchScore score) {
        return ResponseEntity.ok(matchScoreService.saveScore(score));
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<List<MatchScore>> getMatchScores(@PathVariable Long matchId) {
        return ResponseEntity.ok(matchScoreService.getMatchScores(matchId));
    }
}
