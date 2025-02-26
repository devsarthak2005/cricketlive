package com.myproject.cricketlivescore.controller;

import com.myproject.cricketlivescore.model.Ball;
import com.myproject.cricketlivescore.service.BallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ball")
@RequiredArgsConstructor
public class BallController {

    private final BallService ballService;

    @PostMapping("/add")
    public ResponseEntity<Ball> addBall(@RequestBody Ball ball) {
        return ResponseEntity.ok(ballService.addBall(ball));
    }

    @GetMapping("/match/{matchId}")
    public ResponseEntity<List<Ball>> getMatchBalls(@PathVariable Long matchId) {
        return ResponseEntity.ok(ballService.getBallsByMatch(matchId));
    }
}
