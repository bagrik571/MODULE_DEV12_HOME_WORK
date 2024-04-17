package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@Entity
@Table(name = "planet")

public class Planet {

    @Id
    @NotNull
    @Pattern(regexp = "^[A-Z0-9]+$")
    private String id;

    @Column
    @NotNull
    @Size(min=1, max=500)
    private String name;

    @OneToMany(mappedBy="fromPlanet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> fromPlanet = new HashSet<>();

    @OneToMany(mappedBy="toPlanet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> toPlanet = new HashSet<>();
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
