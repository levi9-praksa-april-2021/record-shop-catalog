package com.recordshop.catalog.web;

import lombok.Data;

import java.util.List;

@Data
public class GetRecordsResponse {
    private final List<GetRecordResponse> records;
}
