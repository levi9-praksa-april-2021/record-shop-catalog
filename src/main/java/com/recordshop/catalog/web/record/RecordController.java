package com.recordshop.catalog.web.record;


import com.recordshop.catalog.domain.record.InvalidRecordFilterException;
import com.recordshop.catalog.domain.record.RecordService;
import com.recordshop.catalog.domain.record.Record;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/records", produces= MediaType.APPLICATION_JSON_VALUE)
public class RecordController {
    private final RecordService recordService;
    private final RecordMapper recordMapper;

    @PreAuthorize("hasAuthority('SCOPE_catalog.read')")
    @GetMapping("/{recordId}")
    public ResponseEntity<RecordDTO> getRecord(@PathVariable Long recordId) {
        return recordService.findById(recordId)
                .map(record -> ResponseEntity.ok(recordMapper.toDto(record)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PreAuthorize("hasAuthority('SCOPE_catalog.read')")
    @GetMapping("")
    public ResponseEntity<RecordsDTO> getRecords(
            @RequestParam(name="filter", required = false) String filter
    ) throws InvalidRecordFilterException {
        List<Record> records = recordService.getRecords(filter);
        return ResponseEntity.ok(makeRecordsResponse(records));
    }

    private RecordsDTO makeRecordsResponse(List<Record> records) {
        return new RecordsDTO(recordMapper.toDtoList(records));
    }

    @PreAuthorize("hasAuthority('SCOPE_catalog.write')")
    @PostMapping("")
	public ResponseEntity<RecordDTO> createRecord(@Valid @RequestBody CreateRecordRequest request)  {
    	Record record = recordService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(recordMapper.toDto(record));
	}

    @PreAuthorize("hasAuthority('SCOPE_catalog.write')")
	@PutMapping("/{recordId}")
    public ResponseEntity<RecordDTO> updateRecord(@PathVariable Long recordId, @Valid @RequestBody UpdateRecordRequest request) {
        Record record = recordService.update(recordId, request);
        return ResponseEntity.status(HttpStatus.OK).body(recordMapper.toDto(record));
    }

    @PreAuthorize("hasAuthority('SCOPE_catalog.write')")
	@PutMapping("/updateStock/{recordId}/{stock}")
    public ResponseEntity<RecordDTO> updateStock(@PathVariable Long recordId, @PathVariable Integer stock) {
        Record record = recordService.updateStock(recordId, stock);
        return ResponseEntity.status(HttpStatus.OK).body(recordMapper.toDto(record));
    }

    @PreAuthorize("hasAuthority('SCOPE_catalog.write')")
    @DeleteMapping("/{recordId}")
	public ResponseEntity<Void> deleteRecord(@PathVariable Long recordId)  {
    	recordService.delete(recordId);
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
