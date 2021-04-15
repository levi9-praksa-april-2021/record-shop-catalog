package com.recordshop.catalog.web.record;


import com.recordshop.catalog.domain.record.InvalidRecordFilterException;
import com.recordshop.catalog.domain.record.RecordService;
import com.recordshop.catalog.domain.record.Record;
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
                .map(record -> ResponseEntity.ok(recordMapper.toDto(record)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping(path="")
    public ResponseEntity<RecordsDTO> getRecords(
            @RequestParam(name="filter", required = false) String filter
    ) throws InvalidRecordFilterException {
        List<Record> records = recordService.getRecords(filter);
        return ResponseEntity.ok(makeRecordsResponse(records));
    }

    private RecordsDTO makeRecordsResponse(List<Record> records) {
        return new RecordsDTO(recordMapper.toDtoList(records));
    }
    
    @PostMapping(value = "/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<RecordDTO> saveRecord(@RequestBody RecordDTO recordDTO)  {
    	return ResponseEntity.ok(recordService.save(recordDTO));
	}
    
    @GetMapping(value = "/delete")
	public ResponseEntity<Void> deleteRecord(@RequestParam Long id)  {
    	recordService.delete(id);
    	return ResponseEntity.ok().build();
	}
    
    
    
}
