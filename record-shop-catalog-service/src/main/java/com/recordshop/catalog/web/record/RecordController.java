package com.recordshop.catalog.web.record;


import com.recordshop.catalog.domain.record.Record;
import com.recordshop.catalog.domain.record.RecordSearch;
import com.recordshop.catalog.domain.record.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/records")
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;
    private final RecordMapper recordMapper;

    @GetMapping(path="/{recordId}")
    public ResponseEntity<RecordDTO> getRecord(@PathVariable Long recordId) {
        return recordService.findById(recordId)
                .map(record -> ResponseEntity.ok(makeGetRecordResponse(record)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    private RecordDTO makeGetRecordResponse(Record record) {
        return recordMapper.toDto(record);
    }

    @GetMapping(path="")
    public ResponseEntity<RecordsDTO> getRecords(
            @RequestParam(name="filter", required = false) String filter
    ) {
        return ResponseEntity.ok(makeGetRecordsResponse(recordService.getRecords(filter)));
    }

    private RecordsDTO makeGetRecordsResponse(List<Record> records) {
        return new RecordsDTO(
                recordMapper.toDtoList(records)
        );
    }
}
