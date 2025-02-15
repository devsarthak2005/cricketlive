package com.myproject.cricketlivescore.service;

import com.myproject.cricketlivescore.model.Player;
import com.myproject.cricketlivescore.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PlayerOfTheMatchService {
    @Autowired
    private PlayerRepository playerRepository;

    public Player getPlayerOfTheMatch(Long matchId) {
        List<Player> players = playerRepository.findByMatchId(matchId);

        return players.stream()
                .max(Comparator.comparingInt(Player::getFantasyPoints))
                .orElse(null);
    }
}
