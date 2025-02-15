package com.myproject.cricketlivescore.repository;
import com.myproject.cricketlivescore.model.Tournament;
import com.myproject.cricketlivescore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    List<Tournament> findByCreatedBy(User user);
}

