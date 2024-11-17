package com.example.character.entitiesDTO;

import java.io.Serializable;

import com.example.character.entities.CharacterEntity;

public record CharacterEntityRequestDTO(String name,  String power,  String occupation, String origin, String series, Integer age) implements Serializable {
	public CharacterEntityRequestDTO(CharacterEntity characterEntityMain) {
		this( characterEntityMain.getName(), characterEntityMain.getPower(), characterEntityMain.getOccupation(), characterEntityMain.getOrigin(), characterEntityMain.getSeries(), characterEntityMain.getAge());
	}
}