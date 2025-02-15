package com.myproject.cricketlivescore.service;

import com.myproject.cricketlivescore.model.PlayerStats;
import com.myproject.cricketlivescore.repository.PlayerStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerStatsService {

    private final PlayerStatsRepository playerStatsRepository;

    public PlayerStats updateStats(PlayerStats stats) {
        return playerStatsRepository.save(stats);
    }


    public Optional<PlayerStats> getPlayerStats(Long playerId) {
        return playerStatsRepository.findByPlayerId(playerId);
    }


    public List<PlayerStats> getLeaderboard() {
        return playerStatsRepository.findAll().stream()
                .sorted(Comparator.comparingInt(PlayerStats::getTotalPoints).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }
}
