package com.myproject.cricketlivescore.service;

import com.myproject.cricketlivescore.model.Match;
import com.myproject.cricketlivescore.model.MatchStatus;
import com.myproject.cricketlivescore.model.Role;
import com.myproject.cricketlivescore.model.User;
import com.myproject.cricketlivescore.repository.MatchRepository;
import com.myproject.cricketlivescore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UserRepository userRepository;

    public Match createMatch(Match match, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.getRole() != Role.PREMIUM) {
            throw new RuntimeException("Only premium users can create matches");
        }

        match.setCreatedBy(user);
        return matchRepository.save(match);
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Optional<Match> getMatchById(Long matchId) {
        return matchRepository.findById(matchId);
    }

    public List<Match> getLiveMatches() {
        return matchRepository.findByStatus(MatchStatus.valueOf("LIVE"));
    }

    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    public Match updateMatchScore(Long matchId, int scoreTeamA, int scoreTeamB) {
        Optional<Match> matchOptional = matchRepository.findById(matchId);

        if (matchOptional.isPresent()) {
            Match match = matchOptional.get();
            match.setScoreTeamA(scoreTeamA);
            match.setScoreTeamB(scoreTeamB);
            matchRepository.save(match);
        }
        return null;
    }

    public List<Match> getMatches() {
        return matchRepository.findAll();
    }
}
