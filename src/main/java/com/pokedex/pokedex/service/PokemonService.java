package com.pokedex.pokedex.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pokedex.pokedex.domain.pokemon.Pokemon;
import com.pokedex.pokedex.repository.PokemonRepository;
import jakarta.transaction.Transactional;

@Service
public class PokemonService {

    @Autowired
    PokemonRepository pokemonRepository;

    @Transactional
    public Pokemon save(Pokemon pokemon){
        return pokemonRepository.save(pokemon);
    }

    public Iterable<Pokemon> getAll(){
        return pokemonRepository.findAll();
    }

    public Optional<Pokemon> findById(Long id){
        return pokemonRepository.findById(id);
    }

    @Transactional
    public void delete(Pokemon pokemon){
        pokemonRepository.delete(pokemon);
    }
}
