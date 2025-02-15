package com.myproject.cricketlivescore.controller;

import com.myproject.cricketlivescore.model.Player;
import com.myproject.cricketlivescore.model.ScoreUpdateMessage;
import com.myproject.cricketlivescore.repository.PlayerRepository;
import com.myproject.cricketlivescore.service.FantasyPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private FantasyPointsService fantasyPointsService;

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerStats(@PathVariable Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        return ResponseEntity.ok(player);
    }

    @PostMapping("/update-points")
    public ResponseEntity<String> updatePoints(@RequestBody ScoreUpdateMessage update) {
        fantasyPointsService.updatePoints(update);
        return ResponseEntity.ok("Points updated successfully");
    }
}
