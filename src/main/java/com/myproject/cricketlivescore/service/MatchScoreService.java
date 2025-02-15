package com.myproject.cricketlivescore.service;

import com.myproject.cricketlivescore.model.MatchScore;
import com.myproject.cricketlivescore.repository.MatchScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchScoreService {

    private final MatchScoreRepository matchScoreRepository;

    public MatchScore saveScore(MatchScore score) {
        score.setUpdatedAt(LocalDateTime.now());
        return matchScoreRepository.save(score);
    }

    public List<MatchScore> getMatchScores(Long matchId) {
        return matchScoreRepository.findByMatchId(matchId);
    }
}
