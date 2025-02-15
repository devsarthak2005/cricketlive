package com.myproject.cricketlivescore.repository;

import com.myproject.cricketlivescore.model.Player;
import com.myproject.cricketlivescore.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeam(Team team);
    List<Player> findByMatchId(Long matchId);
    List<Player> findTop10ByOrderByFantasyPointsDesc();
}
