package com.example.character.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.character.entities.CharacterEntityMain;
import com.example.character.entitiesDTO.CharacterEntityRequestDTO;
import com.example.character.entitiesDTO.CharacterEntityResponseDTO;
import com.example.character.repository.CharacterRepository;

@RestController
@CrossOrigin
@RequestMapping("/characterMain")
public class CharacterController {
    
    @Autowired
    private CharacterRepository repository;
    
    @GetMapping
    public List<CharacterEntityResponseDTO> getAll() {
    	
    	List<CharacterEntityResponseDTO> charList = repository.findAll().stream().map(CharacterEntityResponseDTO::new).toList();
        return charList;
    }
    
    @PostMapping
    public void saveCharacter(@RequestBody CharacterEntityRequestDTO data ) {
    	CharacterEntityMain charData = new CharacterEntityMain(data);
    	repository.save(charData);
    	
    	return ;
    }
    
}
