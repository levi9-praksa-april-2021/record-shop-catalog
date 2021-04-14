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
            @RequestParam(name="album", required = false) String album,
            @RequestParam(name="genre", required = false) String genre,
            @RequestParam(name="artist", required = false) String artist
    ) {
        RecordSearch search = new RecordSearch()
                .withAlbum(album)
                .withGenre(genre)
                .withArtist(artist);
        return ResponseEntity.ok(makeGetRecordsResponse(recordService.getRecords(search)));
    }

    private RecordsDTO makeGetRecordsResponse(List<Record> records) {
        return new RecordsDTO(
                recordMapper.toDtoList(records)
        );
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
