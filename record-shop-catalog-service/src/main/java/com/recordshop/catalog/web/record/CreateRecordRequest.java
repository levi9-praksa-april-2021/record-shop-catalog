package com.recordshop.catalog.web.record;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateRecordRequest {
    @NotBlank(message = "Title cannot be blank")
    private final String title;
    @NotBlank(message = "Album cannot be blank")
    private final String album;
    @PositiveOrZero(message = "Price cannot be negative")
    private final BigDecimal price;
    @PositiveOrZero(message = "Stock cannot be negative")
    private final Integer stock;

    @NotEmpty(message = "Record must have at least one artist")
    private final List<Long> artistIds;

    @NotEmpty(message = "Record must have at least one genre")
    private final List<Long> genreIds;
}
