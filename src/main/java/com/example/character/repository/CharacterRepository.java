package com.example.character.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.character.entities.CharacterEntityMain;

public interface CharacterRepository extends JpaRepository<CharacterEntityMain, Long> {
}
