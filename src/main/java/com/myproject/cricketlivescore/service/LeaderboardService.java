package com.myproject.cricketlivescore.service;

import com.myproject.cricketlivescore.model.Player;
import com.myproject.cricketlivescore.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getTopPlayers() {
        return playerRepository.findTop10ByOrderByFantasyPointsDesc();
    }
}
