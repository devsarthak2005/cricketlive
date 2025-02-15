package com.myproject.cricketlivescore.service;

import com.myproject.cricketlivescore.model.Player;
import com.myproject.cricketlivescore.model.ScoreUpdateMessage;
import com.myproject.cricketlivescore.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FantasyPointsService {
    @Autowired
    private PlayerRepository playerRepository;

    public void updatePoints(ScoreUpdateMessage update) {
        Player player = playerRepository.findById(update.getPlayerId())
                .orElseThrow(() -> new RuntimeException("Player not found"));

        int points = (update.getRuns() * 1) +
                (update.getWickets() * 20) +
                (update.getCatches() * 10) +
                (update.getBoundaries() * 2) +
                (update.getSixes() * 3);

        if (update.getRuns() >= 50) points += 10;
        if (update.getRuns() >= 100) points += 20;
        if (update.getWickets() >= 3) points += 30;
        if (update.getWickets() >= 5) points += 50;

        player.setFantasyPoints(player.getFantasyPoints() + points);
        player.setRuns(player.getRuns() + update.getRuns());
        player.setWickets(player.getWickets() + update.getWickets());
        player.setCatches(player.getCatches() + update.getCatches());
        player.setBoundaries(player.getBoundaries() + update.getBoundaries());
        player.setSixes(player.getSixes() + update.getSixes());

        playerRepository.save(player);
    }
}

