package com.myproject.cricketlivescore.controller;

import com.myproject.cricketlivescore.model.PlayerStats;
import com.myproject.cricketlivescore.service.PlayerStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/player-stats")
@RequiredArgsConstructor
public class PlayerStatsController {

    private final PlayerStatsService playerStatsService;

    @PostMapping("/update")
    public ResponseEntity<PlayerStats> updateStats(@RequestBody PlayerStats stats) {
        return ResponseEntity.ok(playerStatsService.updateStats(stats));
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<Optional<PlayerStats>> getPlayerStats(@PathVariable Long playerId) {
        return ResponseEntity.ok(playerStatsService.getPlayerStats(playerId));
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<PlayerStats>> getLeaderboard() {
        return ResponseEntity.ok(playerStatsService.getLeaderboard());
    }
}
