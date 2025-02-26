package com.myproject.cricketlivescore.controller;

import com.myproject.cricketlivescore.dto.MatchRequest;
import com.myproject.cricketlivescore.dto.MatchUpdateRequest;
import com.myproject.cricketlivescore.dto.UserResponse;
import com.myproject.cricketlivescore.model.Match;
import com.myproject.cricketlivescore.service.MatchService;
import com.myproject.cricketlivescore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.*;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final MatchService matchService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PatchMapping("/users/{id}/role")
    public ResponseEntity<String> updateUserRole(@PathVariable Long id, @RequestParam String role) {
        userService.updateUserRole(id, role);
        return ResponseEntity.ok("User role updated successfully");
    }
    @PostMapping("/match/create")
    public ResponseEntity<String> createMatch(@RequestBody Match matchRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        matchService.createMatch(matchRequest, email);
        return ResponseEntity.ok("Match created successfully");
    }


    @PatchMapping("/match/{id}/update")
    public ResponseEntity<String> updateMatch(@PathVariable Long id, @RequestBody MatchUpdateRequest updateRequest) {
        matchService.updateMatch(id, updateRequest);
        return ResponseEntity.ok("Match updated successfully");
    }
}
