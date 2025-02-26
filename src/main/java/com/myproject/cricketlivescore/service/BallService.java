package com.myproject.cricketlivescore.service;

import com.myproject.cricketlivescore.model.Ball;
import com.myproject.cricketlivescore.repository.BallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BallService {

    private final BallRepository ballRepository;

    public Ball addBall(Ball ball) {
        return ballRepository.save(ball);
    }

    public List<Ball> getBallsByMatch(Long matchId) {
        return ballRepository.findByMatchId(matchId);
    }
}
