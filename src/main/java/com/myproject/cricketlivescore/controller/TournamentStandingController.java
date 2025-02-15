package com.myproject.cricketlivescore.controller;

import com.myproject.cricketlivescore.model.TournamentStanding;
import com.myproject.cricketlivescore.service.TournamentStandingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tournament-standings")
public class TournamentStandingController {
    @Autowired
    private TournamentStandingService tournamentStandingService;

    @GetMapping("/{tournamentId}")
    public ResponseEntity<List<TournamentStanding>> getTournamentStandings(@PathVariable Long tournamentId) {
        List<TournamentStanding> standings = tournamentStandingService.getStandings(tournamentId);
        return ResponseEntity.ok(standings);
    }
}

