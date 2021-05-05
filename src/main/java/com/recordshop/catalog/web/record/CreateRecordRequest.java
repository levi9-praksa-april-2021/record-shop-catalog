package com.recordshop.catalog.web.record;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class CreateRecordRequest {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "Album cannot be blank")
    private String album;
    @PositiveOrZero(message = "Price cannot be negative")
    private BigDecimal price;
    @PositiveOrZero(message = "Stock cannot be negative")
    private Integer stock;

    @NotEmpty(message = "Record must have at least one artist")
    private List<Long> artistIds;

    @NotEmpty(message = "Record must have at least one genre")
    private List<Long> genreIds;
}
