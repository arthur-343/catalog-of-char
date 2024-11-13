package com.example.character.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperUtil {

    @Autowired
    private ModelMapper modelMapper;

    public <O, D> D parseObject(O origin, Class<D> destination) {
        return modelMapper.map(origin, destination);
    }

    public <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        return origin.stream()
                     .map(element -> modelMapper.map(element, destination))
                     .collect(Collectors.toList());
    }
}
