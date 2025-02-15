package com.myproject.cricketlivescore.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Team
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private Set<Player> players;

    @ManyToMany(mappedBy = "teams")
    private Set<Match> matches;


}
