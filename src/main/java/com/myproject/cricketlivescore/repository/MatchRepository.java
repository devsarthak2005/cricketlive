package com.myproject.cricketlivescore.repository;

import com.myproject.cricketlivescore.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long>
{
    List<Match> findByStatus(String status);
    Optional<Match> findById(Long id);

}
