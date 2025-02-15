package com.myproject.cricketlivescore.service;

import com.myproject.cricketlivescore.model.Tournament;
import com.myproject.cricketlivescore.model.TournamentStanding;
import com.myproject.cricketlivescore.repository.TournamentStandingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentStandingService {
    @Autowired
    private TournamentStandingRepository standingRepository;

    public void updateStandings(String winnerTeam, String loserTeam, Tournament tournament) {
        TournamentStanding winnerStanding = standingRepository.findByTeamNameAndTournament(winnerTeam, tournament)
                .orElse(new TournamentStanding());
        winnerStanding.setTeamName(winnerTeam);
        winnerStanding.setTournament(tournament);
        winnerStanding.setMatchesPlayed(winnerStanding.getMatchesPlayed() + 1);
        winnerStanding.setWins(winnerStanding.getWins() + 1);
        winnerStanding.setPoints(winnerStanding.getPoints() + 2);
        standingRepository.save(winnerStanding);

        TournamentStanding loserStanding = standingRepository.findByTeamNameAndTournament(loserTeam, tournament)
                .orElse(new TournamentStanding());
        loserStanding.setTeamName(loserTeam);
        loserStanding.setTournament(tournament);
        loserStanding.setMatchesPlayed(loserStanding.getMatchesPlayed() + 1);
        loserStanding.setLosses(loserStanding.getLosses() + 1);
        standingRepository.save(loserStanding);
    }

    public List<TournamentStanding> getStandings(Long tournamentId) {
        return standingRepository.findByTournamentIdOrderByPointsDesc(tournamentId);
    }
}
