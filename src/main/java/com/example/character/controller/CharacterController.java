package com.example.character.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

import com.example.character.data.vo.v1.CharacterDTO;
import com.example.character.service.CharacterService;

@CrossOrigin
@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getAllCharacters() {
        List<CharacterDTO> charList = characterService.getAll();
        charList.forEach(c -> c.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).getCharacterById(c.getId())).withSelfRel()));
        return ResponseEntity.ok(charList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CharacterDTO>> getCharacterById(@PathVariable Long id) {
        CharacterDTO charDTO = characterService.getCharacterById(id);

        EntityModel<CharacterDTO> resource = EntityModel.of(charDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).getCharacterById(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).getAllCharacters()).withRel("all-characters"));

        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<Void> saveCharacter(@RequestBody CharacterDTO data) {
        if (data == null || data.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        characterService.saveCharacter(data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        characterService.deleteCharacter(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> updateCharacter(@PathVariable Long id, @RequestBody CharacterDTO data) {
        try {
            CharacterDTO updatedCharacter = characterService.updateCharacter(id, data);
            return ResponseEntity.ok(updatedCharacter);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
