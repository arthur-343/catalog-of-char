package com.example.character.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.character.data.vo.v1.CharacterDTO;
import com.example.character.entities.CharacterEntity;
import com.example.character.entitiesDTO.CharacterEntityResponseDTO;
import com.example.character.mapper.ModelMapperUtil;
import com.example.character.repository.CharacterRepository;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository repository;
    
    @Autowired
    private ModelMapperUtil modelMapperUtil;
    
    public List<CharacterDTO> getAll() {
        List<CharacterEntity> characters = repository.findAll();
        return modelMapperUtil.parseListObjects(characters, CharacterDTO.class);
    }
    
    public CharacterDTO getCharacterById(Long id) {
        Optional<CharacterEntity> optionalCharacter = repository.findById(id);
        if (optionalCharacter.isPresent()) {
            CharacterEntity charData = optionalCharacter.get();
            return modelMapperUtil.parseObject(charData, CharacterDTO.class);
        } else {
            throw new RuntimeException("Character not found");
        }
    }
    
    public void saveCharacter(@RequestBody CharacterDTO data) {
        CharacterEntity charData = modelMapperUtil.parseObject(data, CharacterEntity.class);
        repository.save(charData);      
    }

    public CharacterDTO updateCharacter(Long id, CharacterDTO data) {
        Optional<CharacterEntity> optionalCharacter = repository.findById(id);
        if (optionalCharacter.isPresent()) {
            CharacterEntity charData = optionalCharacter.get();
            charData.setName(data.getName());
            charData.setPower(data.getPower());
            charData.setOccupation(data.getOccupation());
            charData.setOrigin(data.getOrigin());
            charData.setSeries(data.getSeries());
            charData.setAge(data.getAge());
            repository.save(charData);
            return modelMapperUtil.parseObject(charData, CharacterDTO.class);
        } else {
            throw new RuntimeException("Character not found");
        }      
    }
    
    public void deleteCharacter(Long id) {
        repository.deleteById(id);
    }


}
