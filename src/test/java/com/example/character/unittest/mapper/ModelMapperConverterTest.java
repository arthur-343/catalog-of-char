package com.example.character.unittest.mapper;

import java.util.ArrayList;
import java.util.List;

import com.example.character.data.vo.v1.CharacterDTO;
import com.example.character.entities.CharacterEntity;

public class ModelMapperConverterTest {

    public CharacterEntity mockEntity() {
        return mockEntity(0);
    }
    
    public CharacterDTO mockDTO() {
        return mockDTO(0);
    }
    
    public List<CharacterEntity> mockEntityList() {
        List<CharacterEntity> characters = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            characters.add(mockEntity(i));
        }
        return characters;
    }

    public List<CharacterDTO> mockDTOList() {
        List<CharacterDTO> characters = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            characters.add(mockDTO(i));
        }
        return characters;
    }
    
    public CharacterEntity mockEntity(Integer number) {
        CharacterEntity character = new CharacterEntity();
        character.setId(number.longValue());
        character.setName("Name Test" + number);
        character.setPower("Power Test" + number);
        character.setOccupation("Occupation Test" + number);
        character.setOrigin("Origin Test" + number);
        character.setSeries("Series Test" + number);
        character.setAge(number);
        return character;
    }

    public CharacterDTO mockDTO(Integer number) {
        CharacterDTO character = new CharacterDTO();
        character.setId(number.longValue());
        character.setName("Name Test" + number);
        character.setPower("Power Test" + number);
        character.setOccupation("Occupation Test" + number);
        character.setOrigin("Origin Test" + number);
        character.setSeries("Series Test" + number);
        character.setAge(number);
        return character;
    }
}
