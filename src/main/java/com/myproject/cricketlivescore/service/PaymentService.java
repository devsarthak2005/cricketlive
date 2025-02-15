package com.myproject.cricketlivescore.service;

import com.myproject.cricketlivescore.model.PremiumUser;
import com.myproject.cricketlivescore.model.User;
import com.myproject.cricketlivescore.repository.PremiumUserRepository;
import com.myproject.cricketlivescore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentService {
    @Autowired
    private PremiumUserRepository premiumUserRepository;
    @Autowired
    private UserRepository userRepository;

    public String createSubscription(Long userId, String planType) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate start = LocalDate.now();
        LocalDate end = planType.equals("MONTHLY") ? start.plusMonths(1) : start.plusYears(1);

        PremiumUser premiumUser = new PremiumUser();
        premiumUser.setUser(user);
        premiumUser.setPlanType(planType);
        premiumUser.setSubscriptionStart(start);
        premiumUser.setSubscriptionEnd(end);
        premiumUser.setActive(true);

        premiumUserRepository.save(premiumUser);
        return "Subscription activated for " + user.getUsername();
    }

    public boolean isUserPremium(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return premiumUserRepository.findByUser(user)
                .map(PremiumUser::isActive)
                .orElse(false);
    }
}
