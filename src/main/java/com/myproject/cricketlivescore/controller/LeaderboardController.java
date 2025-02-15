package com.myproject.cricketlivescore.controller;

import com.myproject.cricketlivescore.model.Player;
import com.myproject.cricketlivescore.repository.PlayerRepository;
import com.myproject.cricketlivescore.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private LeaderboardService leaderboardService;

    @GetMapping
    public ResponseEntity<List<Player>> getLeaderboard() {
        List<Player> players = playerRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(Player::getFantasyPoints).reversed())
                .collect(Collectors.toList());
        return ResponseEntity.ok(players);
    }

    @GetMapping("/top-players")
    public ResponseEntity<List<Player>> getTopPlayers() {
        return ResponseEntity.ok(leaderboardService.getTopPlayers());
    }
}
