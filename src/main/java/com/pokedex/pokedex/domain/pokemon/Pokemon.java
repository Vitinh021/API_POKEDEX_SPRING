package com.pokedex.pokedex.domain.pokemon;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Pokemons")
@Data
public class Pokemon implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name="name", length = 255, nullable = false)
    private String name;

    @Column(name="description", length = 255, nullable = false)
    private String description;

    @Column(name="type", nullable = false)
    private int type;
}
