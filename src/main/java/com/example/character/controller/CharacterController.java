package com.example.character.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.character.entitiesDTO.CharacterEntityRequestDTO;
import com.example.character.entitiesDTO.CharacterEntityResponseDTO;
import com.example.character.service.CharacterService;

@CrossOrigin
@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public List<CharacterEntityResponseDTO> getAllCharacters() {
        return characterService.getAll();
    }

    @GetMapping("/{id}") public CharacterEntityResponseDTO getCharacterById(@PathVariable Long id) {
    	return characterService.getCharacterById(id); 
    	}
    
    @PostMapping
    public ResponseEntity<Void> saveCharacter(@RequestBody CharacterEntityRequestDTO data) {
        characterService.saveCharacter(data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        characterService.deleteCharacter(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterEntityResponseDTO> updateCharacter(@PathVariable Long id, @RequestBody CharacterEntityRequestDTO data) {
        try {
            CharacterEntityResponseDTO updatedCharacter = characterService.updateCharacter(id, data);
            return ResponseEntity.ok(updatedCharacter);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
