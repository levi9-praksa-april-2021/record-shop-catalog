package com.recordshop.catalog.web;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GetRecordResponse {
    private final Long id;
    private final String title;
    private final String album;
    private final BigDecimal price;
    private final Integer stock;
    private final List<GetArtistResponse> artists;
    private final List<GetGenreResponse> genres;
}
