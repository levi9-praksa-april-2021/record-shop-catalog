package com.recordshop.catalog.web.genre;

import com.recordshop.catalog.domain.genre.Genre;


import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDTO toDto(Genre genre);
    List<GenreDTO> map(List<Genre> genres);
}
