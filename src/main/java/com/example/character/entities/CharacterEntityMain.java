package com.example.character.entities;

import com.example.character.entitiesDTO.CharacterEntityRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "character")
@Entity(name = "CharacterEntityMain")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CharacterEntityMain {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String power;
    private String occupation;
    private String origin;
    private String series;
    private Integer age;
    
    public CharacterEntityMain(CharacterEntityRequestDTO characterEntityRequestDTO) {
    	this.name = characterEntityRequestDTO.name();
    	this.power = characterEntityRequestDTO.power();
    	this.occupation = characterEntityRequestDTO.occupation();
    	this.origin = characterEntityRequestDTO.origin();
    	this.series = characterEntityRequestDTO.series();
    	this.age = characterEntityRequestDTO.age();
    }
}
