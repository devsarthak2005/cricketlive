package com.myproject.cricketlivescore.controller;

import com.myproject.cricketlivescore.model.Tournament;
import com.myproject.cricketlivescore.security.JwtUtil;
import com.myproject.cricketlivescore.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    @PostMapping("/create")
    public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament, @RequestHeader("Authorization") String token) {
        JwtUtil jwtUtil = new JwtUtil();
        String email = jwtUtil.extractEmail(token.substring(7)); // Remove 'Bearer '
        return ResponseEntity.ok(tournamentService.createTournament(tournament, email));
    }
}
