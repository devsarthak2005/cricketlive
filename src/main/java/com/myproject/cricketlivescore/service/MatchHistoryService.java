package com.myproject.cricketlivescore.service;

import com.myproject.cricketlivescore.model.Match;
import com.myproject.cricketlivescore.model.MatchHistory;
import com.myproject.cricketlivescore.model.Player;
import com.myproject.cricketlivescore.repository.MatchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchHistoryService {
    @Autowired
    private MatchHistoryRepository matchHistoryRepository;
    @Autowired
    private PlayerOfTheMatchService playerOfTheMatchService;

    public void saveMatchResult(Match match, String winnerTeam) {
        MatchHistory history = new MatchHistory();
        history.setMatch(match);
        history.setWinnerTeam(winnerTeam);
        history.setTotalRuns(match
                .getTotalRuns());
        history.setTotalWickets(match.getTotalWickets());

        Player playerOfTheMatch = playerOfTheMatchService.getPlayerOfTheMatch(match.getId());
        history.setPlayerOfTheMatch(playerOfTheMatch != null ? playerOfTheMatch.getName() : "N/A");

        matchHistoryRepository.save(history);
    }

    public List<MatchHistory> getMatchHistory(Long tournamentId) {
        return matchHistoryRepository.findByMatch_TournamentId(tournamentId);
    }
}
