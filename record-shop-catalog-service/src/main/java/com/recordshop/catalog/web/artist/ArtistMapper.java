package com.recordshop.catalog.web.artist;

import com.recordshop.catalog.domain.artist.Artist;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArtistMapper {
    ArtistMapper MAPPER = Mappers.getMapper(ArtistMapper.class);

    ArtistDTO toDto(Artist artist);
    
    List<ArtistDTO> map(List<Artist> artists);
}
