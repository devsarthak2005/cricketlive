package com.myproject.cricketlivescore.repository;

import com.myproject.cricketlivescore.model.PremiumUser;
import com.myproject.cricketlivescore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PremiumUserRepository extends JpaRepository<PremiumUser, Long> {
    Optional<PremiumUser> findByUser(User user);
}
