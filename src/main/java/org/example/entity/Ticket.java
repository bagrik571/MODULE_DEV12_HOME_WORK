package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ticket")

public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "created_at")
    private Timestamp date;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "from_planet_id")
    private Planet fromPlanet;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "to_planet_id")
    private Planet toPlanet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Planet getFromPlanet() {
        return fromPlanet;
    }

    public void setFromPlanet(Planet fromPlanet) {
        this.fromPlanet = fromPlanet;
    }

    public Planet getToPlanet() {
        return toPlanet;
    }

    public void setToPlanet(Planet toPlanet) {
        this.toPlanet = toPlanet;
    }

    public Ticket(Client client, Planet fromPlanet, Planet toPlanet) {
        this.client = client;
        this.date = date;
        this.fromPlanet = fromPlanet;
        this.toPlanet = toPlanet;
    }
    public Ticket() {

    }
    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", client=" + (client != null ? "Client{id=" + client.getId() + ", name='" + client.getName() + "'}" : null) +
                ", date=" + date +
                ", fromPlanet=" + fromPlanet +
                ", toPlanet=" + toPlanet +
                '}';
    }
    @PrePersist
    protected void onCreate() {
        date = new Timestamp(System.currentTimeMillis());
    }
}
