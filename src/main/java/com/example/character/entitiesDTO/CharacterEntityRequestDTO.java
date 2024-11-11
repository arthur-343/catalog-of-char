package com.example.character.entitiesDTO;

import com.example.character.entities.CharacterEntityMain;

public record CharacterEntityRequestDTO(String name,  String power,  String occupation, String origin, String series, Integer age) {
	public CharacterEntityRequestDTO(CharacterEntityMain characterEntityMain) {
		this( characterEntityMain.getName(), characterEntityMain.getPower(), characterEntityMain.getOccupation(), characterEntityMain.getOrigin(), characterEntityMain.getSeries(), characterEntityMain.getAge());
	}
}