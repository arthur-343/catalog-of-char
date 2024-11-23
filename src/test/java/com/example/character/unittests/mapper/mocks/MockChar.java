package com.example.character.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.List;

import com.example.character.data.vo.v1.CharacterDTO;

public class MockChar {

    public CharacterDTO mockEntity() {
        return mockEntity(0);
    }
    
    public CharacterDTO mockEntity(Integer number) {
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
    
    public List<CharacterDTO> mockEntityList() {
        List<CharacterDTO> characters = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            characters.add(mockEntity(i));
        }
        return characters;
    }
}
