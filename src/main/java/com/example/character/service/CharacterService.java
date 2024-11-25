package com.example.character.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.character.data.vo.v1.CharacterDTO;
import com.example.character.entities.CharacterEntity;
import com.example.character.exception.DatabaseException;
import com.example.character.exception.InvalidFieldException;
import com.example.character.exception.MissingFieldException;
import com.example.character.exception.NotFindException;
import com.example.character.mapper.ModelMapperUtil;
import com.example.character.repository.CharacterRepository;
import com.example.character.util.CharacterValidator;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository repository;
    
    @Autowired
    private ModelMapperUtil modelMapperUtil;
    

    public List<CharacterDTO> getAll() {
        try {
            List<CharacterEntity> characters = repository.findAll();
            return modelMapperUtil.parseListObjects(characters, CharacterDTO.class);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFindException("No characters found: " + e.getMessage());
        } catch (Exception e) {
            throw new DatabaseException("An error occurred while retrieving characters: " + e.getMessage());
        }
    }

    public CharacterDTO getCharacterById(Long id) {
        Optional<CharacterEntity> optionalCharacter = repository.findById(id);
        if (optionalCharacter.isPresent()) {
            CharacterEntity charData = optionalCharacter.get();
            return modelMapperUtil.parseObject(charData, CharacterDTO.class);
        } else {
            throw new NotFindException("Character not found");
        }
    }

    public void saveCharacter(@RequestBody CharacterDTO data) {
        if (CharacterValidator.isFieldMissingOrEmpty(data)) {
            throw new MissingFieldException("One or more required fields are missing or empty");
        }
        try {
            CharacterEntity charData = modelMapperUtil.parseObject(data, CharacterEntity.class);
            repository.save(charData);
        } catch (ConstraintViolationException e) {
            throw new InvalidFieldException("Constraint violation: " + e.getMessage());
        } catch (Exception e) {
            throw new DatabaseException("An error occurred while saving the character: " + e.getMessage());
        }
    }



    public CharacterDTO updateCharacter(Long id, CharacterDTO data) {
        Optional<CharacterEntity> optionalCharacter = repository.findById(id);
        if (optionalCharacter.isPresent()) {
            try {
                CharacterEntity charData = optionalCharacter.get();
                charData.setName(data.getName());
                charData.setPower(data.getPower());
                charData.setOccupation(data.getOccupation());
                charData.setOrigin(data.getOrigin());
                charData.setSeries(data.getSeries());
                charData.setAge(data.getAge());
                repository.save(charData);
                return modelMapperUtil.parseObject(charData, CharacterDTO.class);
            } catch (DataIntegrityViolationException e) {
                throw new InvalidFieldException("Invalid field detected in the request: " + e.getMessage());
            } catch (OptimisticLockingFailureException e) {
                throw new DatabaseException("Optimistic locking failure: " + e.getMessage());
            } catch (TransactionSystemException e) { throw new DatabaseException("Transaction system failure: " + e.getMessage());
            } catch (Exception e) {
                throw new DatabaseException("An error occurred while updating the character: " + e.getMessage());
            }
        } else {
            throw new NotFindException("Character not found");
        }
    }

    public void deleteCharacter(Long id) {   
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new NotFindException("Character not found: " + e.getMessage());
        }
    }
}
