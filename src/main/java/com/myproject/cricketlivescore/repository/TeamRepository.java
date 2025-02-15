package com.myproject.cricketlivescore.repository;

import com.myproject.cricketlivescore.model.Player;
import com.myproject.cricketlivescore.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Player, Long>
{
    List<Player> findByTeam(Team team);
}
