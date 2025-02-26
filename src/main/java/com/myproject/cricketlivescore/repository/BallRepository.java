package com.myproject.cricketlivescore.repository;

import com.myproject.cricketlivescore.model.Ball;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BallRepository extends JpaRepository<Ball, Long> {
    List<Ball> findByMatchId(Long matchId);
}
