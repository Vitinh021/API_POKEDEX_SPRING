package com.pokedex.pokedex.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokedex.pokedex.domain.pokemon.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long>{
    
}
