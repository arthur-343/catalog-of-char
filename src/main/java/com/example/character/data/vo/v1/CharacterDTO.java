package com.example.character.data.vo.v1;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CharacterDTO extends RepresentationModel<CharacterDTO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
    
    private String name;
    private String power;
    private String occupation;
    private String origin;
    private String series;
    private Integer age;
    

}
