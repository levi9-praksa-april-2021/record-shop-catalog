package com.recordshop.catalog.web.genre;

import com.recordshop.catalog.domain.genre.Genre;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenreMapper {
    GenreMapper MAPPER = Mappers.getMapper(GenreMapper.class);

    GenreDTO toDto(Genre genre);
    List<GenreDTO> map(List<Genre> genres);
}
