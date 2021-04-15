package com.recordshop.catalog.web.record;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateRecordRequest {
    private final String title;
    private final String album;
    private final BigDecimal price;
    private final Integer stock;

    private final List<Long> artistIds;
    private final List<Long> genreIds;
}
