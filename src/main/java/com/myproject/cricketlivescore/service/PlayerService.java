package com.myproject.cricketlivescore.service;

import com.myproject.cricketlivescore.model.Player;
import com.myproject.cricketlivescore.model.Team;
import com.myproject.cricketlivescore.repository.PlayerRepository;
import com.myproject.cricketlivescore.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    public List<Player> getPlayersByTeam(Long teamId) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found")).getTeam();
        return playerRepository.findByTeam(team);
    }
}
