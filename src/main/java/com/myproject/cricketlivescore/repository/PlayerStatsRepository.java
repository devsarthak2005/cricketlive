package com.myproject.cricketlivescore.repository;

import com.myproject.cricketlivescore.model.PlayerStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Long>
{

    Optional<PlayerStats> findByPlayerId(Long playerId);

    @Query("SELECT p FROM Player p ORDER BY p.fantasyPoints DESC")
    List<PlayerStats> findAllByOrderByTotalPointsDesc();
}
