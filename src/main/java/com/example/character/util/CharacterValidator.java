package com.example.character.util;

import com.example.character.data.vo.v1.CharacterDTO;

public class CharacterValidator {

    public static boolean isFieldMissingOrEmpty(CharacterDTO data) {
        return data.getName() == null || data.getName().isEmpty() ||
               data.getPower() == null || data.getPower().isEmpty() ||
               data.getOccupation() == null || data.getOccupation().isEmpty() ||
               data.getOrigin() == null || data.getOrigin().isEmpty() ||
               data.getSeries() == null || data.getSeries().isEmpty() ||
               data.getAge() == null;
    }
}
