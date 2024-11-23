package com.example.character.unittest.mockito.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.character.data.vo.v1.CharacterDTO;
import com.example.character.entities.CharacterEntity;
import com.example.character.mapper.ModelMapperUtil;
import com.example.character.repository.CharacterRepository;
import com.example.character.service.CharacterService;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class CharacterServiceTest {

    @Mock
    private CharacterRepository repository;
    
    @Mock
    private ModelMapperUtil modelMapperUtil;
    
    @InjectMocks
    private CharacterService characterService;
    
    private CharacterDTO charDTO;
    private CharacterEntity charEntity;
    
    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        charDTO = mockJoeYabuki();
        charEntity = mockJoeYabukiEntity();
    }

    private CharacterDTO mockJoeYabuki() {
        return new CharacterDTO(
            1L,
            "Joe Yabuki",
            "Boxing",
            "Boxer",
            "Tokyo, Japan",
            "Ashita no Joe",
            23
        );
    }

    private CharacterEntity mockJoeYabukiEntity() {
        return new CharacterEntity(
            1L,
            "Joe Yabuki",
            "Boxing",
            "Boxer",
            "Tokyo, Japan",
            "Ashita no Joe",
            23
        );
    }

    @Test
    void testGetAll() {
        // Arrange
        List<CharacterEntity> charEntityList = Collections.singletonList(charEntity);
        List<CharacterDTO> charDTOList = Collections.singletonList(charDTO);
        
        when(repository.findAll()).thenReturn(charEntityList);
        when(modelMapperUtil.parseListObjects(charEntityList, CharacterDTO.class)).thenReturn(charDTOList);

        // Act
        List<CharacterDTO> result = characterService.getAll();

        // Assert
        assertEquals(charDTOList.size(), result.size());
        assertEquals(charDTO.getId(), result.get(0).getId());
        assertEquals(charDTO.getName(), result.get(0).getName());
        assertEquals(charDTO.getPower(), result.get(0).getPower());
        assertEquals(charDTO.getOccupation(), result.get(0).getOccupation());
        assertEquals(charDTO.getOrigin(), result.get(0).getOrigin());
        assertEquals(charDTO.getSeries(), result.get(0).getSeries());
        assertEquals(charDTO.getAge(), result.get(0).getAge());
        assertNotNull(result);
     }

    @Test
    void testGetCharacterById() {
        // Arrange
        when(repository.findById(charDTO.getId())).thenReturn(Optional.of(charEntity));
        when(modelMapperUtil.parseObject(charEntity, CharacterDTO.class)).thenReturn(charDTO);

        // Act
        CharacterDTO result = characterService.getCharacterById(charDTO.getId());

        // Assert
        assertEquals(charDTO.getId(), result.getId());
        assertEquals(charDTO.getName(), result.getName());
        assertEquals(charDTO.getPower(), result.getPower());
        assertEquals(charDTO.getOccupation(), result.getOccupation());
        assertEquals(charDTO.getOrigin(), result.getOrigin());
        assertEquals(charDTO.getSeries(), result.getSeries());
        assertEquals(charDTO.getAge(), result.getAge());
    }

    @Test
    void testSaveCharacter() {
        // Arrange
        when(modelMapperUtil.parseObject(charDTO, CharacterEntity.class)).thenReturn(charEntity);
        when(repository.save(any(CharacterEntity.class))).thenReturn(charEntity);


        // Act
        characterService.saveCharacter(charDTO);

        // Assert
        verify(repository, times(1)).save(charEntity);
    }



    @Test
    void testUpdateCharacter() {
        // Arrange
        Long id = charDTO.getId();
        when(repository.findById(id)).thenReturn(Optional.of(charEntity));
        when(modelMapperUtil.parseObject(charEntity, CharacterDTO.class)).thenReturn(charDTO);
        when(repository.save(any(CharacterEntity.class))).thenReturn(charEntity);

        // Act
        CharacterDTO result = characterService.updateCharacter(id, charDTO);

        // Assert
        assertEquals(charDTO.getId(), result.getId());
        assertEquals(charDTO.getName(), result.getName());
        assertEquals(charDTO.getPower(), result.getPower());
        assertEquals(charDTO.getOccupation(), result.getOccupation());
        assertEquals(charDTO.getOrigin(), result.getOrigin());
        assertEquals(charDTO.getSeries(), result.getSeries());
        assertEquals(charDTO.getAge(), result.getAge());
    }

    @Test
    void testUpdateCharacterNotFound() {
        // Arrange
        Long id = charDTO.getId();
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        try {
            characterService.updateCharacter(id, charDTO);
            fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertEquals("Character not found", e.getMessage());
        }
    }

    @Test
    void testDeleteCharacter() {
        // Arrange
        doNothing().when(repository).deleteById(charDTO.getId());

        // Act
        characterService.deleteCharacter(charDTO.getId());

        // Assert
        verify(repository, times(1)).deleteById(charDTO.getId());
    }

}
