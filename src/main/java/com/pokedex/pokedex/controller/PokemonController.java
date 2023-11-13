package com.pokedex.pokedex.controller;

import java.util.Optional;

import com.pokedex.pokedex.domain.pokemon.Pokemon;
import com.pokedex.pokedex.domain.pokemon.PokemonDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pokedex.pokedex.service.PokemonService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pokemon")
public class PokemonController {
    @Autowired
    PokemonService pokemonService;

    @GetMapping
    public Iterable<Pokemon> getAllPokemons(){
        return pokemonService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePokemon(@PathVariable(value = "id") Long id){
        Optional<Pokemon> pokemonOpt = pokemonService.findById(id);
        if(!pokemonOpt.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokemon not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pokemonOpt.get());
    }

    @PostMapping
    public ResponseEntity<Object> savePokemon(@RequestBody @Valid PokemonDto pokemonDto){
        var pokemon = new Pokemon();
        BeanUtils.copyProperties(pokemonDto, pokemon);
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonService.save(pokemon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePokemon(@PathVariable(value = "id") Long id){
        Optional<Pokemon> pokemonOpt = pokemonService.findById(id);
        if(!pokemonOpt.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokemon not found!!");
        }
        pokemonService.delete(pokemonOpt.get());
        return ResponseEntity.status(HttpStatus.OK).body("Excluded with success!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePokemon(@PathVariable(value = "id") Long id, @RequestBody @Valid PokemonDto pokemonDto){
        Optional<Pokemon> pokemonOpt = pokemonService.findById(id);
        if(!pokemonOpt.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokemon not found!");
        }
        var pokemon = pokemonOpt.get();
        pokemon.setName(pokemonDto.name());
        pokemon.setDescription(pokemonDto.description());
        pokemon.setType(pokemonDto.type());

        return ResponseEntity.status(HttpStatus.OK).body(pokemonService.save(pokemon));
    }
}
