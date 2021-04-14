package com.recordshop.catalog.web.record;

import com.recordshop.catalog.web.artist.ArtistDTO;
import com.recordshop.catalog.web.genre.GenreDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RecordDTO {
    private Long id;
    private String title;
    private String album;
    private BigDecimal price;
    private Integer stock;
    private List<ArtistDTO> artists;
    private List<GenreDTO> genres;
}
