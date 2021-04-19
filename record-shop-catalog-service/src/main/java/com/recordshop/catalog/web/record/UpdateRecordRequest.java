package com.recordshop.catalog.web.record;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;

@Data
public class UpdateRecordRequest {
    @NotBlank(message = "Title cannot be blank")
    private final String title;
    @NotBlank(message = "Album cannot be blank")
    private final String album;
    @PositiveOrZero(message = "Price cannot be negative")
    private final BigDecimal price;

    @NotEmpty(message = "Record must have at least one artist")
    private final List<Long> artistIds;

    @NotEmpty(message = "Record must have at least one genre")
    private final List<Long> genreIds;
}
