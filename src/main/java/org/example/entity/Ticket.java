package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "ticket")

public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="client_id")
    private Long clientId;

    @Column(name="created_at")
    private Timestamp date;

    @Column(name="from_planet_id")
    private String fromPlanetId;

    @Column(name="to_planet_id")
    private String toPlanetId;

}
