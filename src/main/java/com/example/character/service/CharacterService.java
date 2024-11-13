package com.example.character.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.character.entities.CharacterEntityMain;
import com.example.character.entitiesDTO.CharacterEntityRequestDTO;
import com.example.character.entitiesDTO.CharacterEntityResponseDTO;
import com.example.character.mapper.ModelMapperUtil;
import com.example.character.repository.CharacterRepository;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository repository;
    
    @Autowired
    private ModelMapperUtil modelMapperUtil;
    
    public List<CharacterEntityResponseDTO> getAll() {
        List<CharacterEntityMain> characters = repository.findAll();
        return modelMapperUtil.parseListObjects(characters, CharacterEntityResponseDTO.class);
    }
    
    public CharacterEntityResponseDTO getCharacterById(Long id) {
        Optional<CharacterEntityMain> optionalCharacter = repository.findById(id);
        if (optionalCharacter.isPresent()) {
            CharacterEntityMain charData = optionalCharacter.get();
            return modelMapperUtil.parseObject(charData, CharacterEntityResponseDTO.class);
        } else {
            throw new RuntimeException("Character not found");
        }
    }
    
    public void saveCharacter(@RequestBody CharacterEntityRequestDTO data) {
        CharacterEntityMain charData = modelMapperUtil.parseObject(data, CharacterEntityMain.class);
        repository.save(charData);      
    }

    public CharacterEntityResponseDTO updateCharacter(Long id, CharacterEntityRequestDTO data) {
        Optional<CharacterEntityMain> optionalCharacter = repository.findById(id);
        if (optionalCharacter.isPresent()) {
            CharacterEntityMain charData = optionalCharacter.get();
            charData.setName(data.name());
            charData.setPower(data.power());
            charData.setOccupation(data.occupation());
            charData.setOrigin(data.origin());
            charData.setSeries(data.series());
            charData.setAge(data.age());
            repository.save(charData);
            return modelMapperUtil.parseObject(charData, CharacterEntityResponseDTO.class);
        } else {
            throw new RuntimeException("Character not found");
        }      
    }
    
    public void deleteCharacter(Long id) {
        repository.deleteById(id);
    }


}
