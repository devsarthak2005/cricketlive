package com.myproject.cricketlivescore.repository;

import com.myproject.cricketlivescore.model.Tournament;
import com.myproject.cricketlivescore.model.TournamentStanding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentStandingRepository extends JpaRepository<TournamentStanding, Long> {

    Optional<TournamentStanding> findByTeamNameAndTournament(String teamName, Tournament tournament);

    List<TournamentStanding> findByTournamentIdOrderByPointsDesc(Long tournamentId);
}
