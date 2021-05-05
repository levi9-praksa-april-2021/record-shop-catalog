package com.recordshop.catalog.web.record;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class UpdateRecordRequest {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "Album cannot be blank")
    private String album;
    @PositiveOrZero(message = "Price cannot be negative")
    private BigDecimal price;

    @NotEmpty(message = "Record must have at least one artist")
    private List<Long> artistIds;

    @NotEmpty(message = "Record must have at least one genre")
    private List<Long> genreIds;
}
