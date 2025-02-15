package com.myproject.cricketlivescore.controller;

import com.myproject.cricketlivescore.model.Match;
import com.myproject.cricketlivescore.model.Player;
import com.myproject.cricketlivescore.model.PremiumUser;
import com.myproject.cricketlivescore.model.User;
import com.myproject.cricketlivescore.repository.PremiumUserRepository;
import com.myproject.cricketlivescore.repository.UserRepository;
import com.myproject.cricketlivescore.service.MatchService;
import com.myproject.cricketlivescore.service.PlayerOfTheMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/premium/match")
public class MatchController {
    @Autowired
    private MatchService matchService;

    @Autowired
    private PremiumUserRepository premiumUserRepository;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/create")
    public ResponseEntity<String> createMatch(@RequestParam Long userId, @RequestBody Match match) {
        boolean isPremium = premiumUserRepository.findByUser(new User(userId))
                .map(PremiumUser::isActive).orElse(false);

        if (!isPremium) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only premium users can create matches");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        String email = user.getEmail();

        matchService.createMatch(match,email);
        return ResponseEntity.ok("Match created successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Match>> getAllMatches() {
        return ResponseEntity.ok(matchService.getMatches());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Match>> getMatchById(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.getMatchById(id));
    }

    @GetMapping("/live")
    public ResponseEntity<List<Match>> getLiveMatches() {
        return ResponseEntity.ok(matchService.getLiveMatches());
    }

    @PostMapping
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        return ResponseEntity.ok(matchService.saveMatch(match));
    }

    @PutMapping("/{matchId}/update-score")
    public ResponseEntity<Match> updateMatchScore(@PathVariable Long matchId,
                                                  @RequestParam int scoreA,
                                                  @RequestParam int scoreB) throws IOException {
        Match updatedMatch = matchService.updateMatchScore(matchId, scoreA, scoreB);
        return ResponseEntity.ok(updatedMatch);
    }


    @GetMapping("/{matchId}")
    public ResponseEntity<Optional<Match>> getMatchDetails(@PathVariable Long matchId) {
        Optional<Optional<Match>> match = Optional.ofNullable(matchService.getMatchById(matchId));
        return match.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Autowired
    private PlayerOfTheMatchService playerOfTheMatchService;

    @GetMapping("/{matchId}/player-of-the-match")
    public ResponseEntity<Player> getPlayerOfTheMatch(@PathVariable Long matchId) {
        Player player = playerOfTheMatchService.getPlayerOfTheMatch(matchId);
        if (player == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(player);
    }
}
