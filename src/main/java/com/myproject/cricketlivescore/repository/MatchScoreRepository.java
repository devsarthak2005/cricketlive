package com.myproject.cricketlivescore.repository;

import com.myproject.cricketlivescore.model.MatchScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchScoreRepository extends JpaRepository<MatchScore, Long>
{
    List<MatchScore> findByMatchId(Long matchId);
}
