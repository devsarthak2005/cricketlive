package com.myproject.cricketlivescore.service;


import com.myproject.cricketlivescore.model.Role;
import com.myproject.cricketlivescore.model.Tournament;
import com.myproject.cricketlivescore.model.User;
import com.myproject.cricketlivescore.repository.TournamentRepository;
import com.myproject.cricketlivescore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private UserRepository userRepository;

    public Tournament createTournament(Tournament tournament, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.getRole() != Role.PREMIUM) {
            throw new RuntimeException("Only premium users can create tournaments");
        }

        tournament.setCreatedBy(user);

        return tournamentRepository.save(tournament);
    }
}
