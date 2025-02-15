package com.myproject.cricketlivescore.repository;

import com.myproject.cricketlivescore.model.MatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchHistoryRepository extends JpaRepository<MatchHistory, Long> {
    List<MatchHistory> findByMatch_TournamentId(Long tournamentId);
}

