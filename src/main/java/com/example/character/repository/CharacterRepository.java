package com.example.character.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.character.entities.CharacterEntity;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
}
