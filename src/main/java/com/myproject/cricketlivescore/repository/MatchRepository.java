package com.myproject.cricketlivescore.repository;

import com.myproject.cricketlivescore.model.Match;
import com.myproject.cricketlivescore.model.MatchStatus;
import com.myproject.cricketlivescore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByCreatedBy(User user);
    List<Match> findByStatus(MatchStatus matchStatus);
}

