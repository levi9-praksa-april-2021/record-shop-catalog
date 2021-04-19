package com.recordshop.catalog.web.record;

import lombok.Data;

import java.util.List;

@Data
public class RecordsDTO {
    private final List<RecordDTO> records;
}
