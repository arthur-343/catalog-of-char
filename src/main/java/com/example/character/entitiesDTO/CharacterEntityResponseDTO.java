package com.example.character.entitiesDTO;

import com.example.character.entities.CharacterEntity;

public record CharacterEntityResponseDTO(
        Long id,
        String name,
        String power,
        String occupation,
        String origin,
        String series,
        Integer age
) {
    public CharacterEntityResponseDTO(CharacterEntity characterEntityMain) {
        this(
            characterEntityMain.getId(),
            characterEntityMain.getName(),
            characterEntityMain.getPower(),
            characterEntityMain.getOccupation(),
            characterEntityMain.getOrigin(),
            characterEntityMain.getSeries(),
            characterEntityMain.getAge()
        );
    }
}
