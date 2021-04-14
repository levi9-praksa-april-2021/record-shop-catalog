package com.recordshop.catalog.web;


import com.recordshop.catalog.domain.*;
import com.recordshop.catalog.domain.record.Record;
import com.recordshop.catalog.domain.record.RecordSearch;
import com.recordshop.catalog.domain.record.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/records")
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;

    @GetMapping(path="/{recordId}")
    public ResponseEntity<GetRecordResponse> getRecord(@PathVariable Long recordId) {
        return recordService.findById(recordId)
                .map(record -> ResponseEntity.ok(makeGetRecordResponse(record)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping(path="")
    public ResponseEntity<GetRecordsResponse> getRecords(
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

    private GetRecordsResponse makeGetRecordsResponse(List<Record> records) {
        return new GetRecordsResponse(
                records.stream()
                        .map(this::makeGetRecordResponse)
                        .collect(Collectors.toList())
        );
    }

    private GetRecordResponse makeGetRecordResponse(Record record) {
        return new GetRecordResponse(
                record.getId(),
                record.getTitle(),
                record.getAlbum(),
                record.getPrice(),
                record.getStock(),
                record.getArtists().stream().map(this::makeGetArtistResponse).collect(Collectors.toList()),
                record.getGenres().stream().map(this::makeGetGenreResponse).collect(Collectors.toList())
        );
    }

    private GetArtistResponse makeGetArtistResponse(Artist artist) {
        return new GetArtistResponse(
                artist.getId(),
                artist.getFirstName(),
                artist.getLastName()
        );
    }

    private GetGenreResponse makeGetGenreResponse(Genre genre) {
        return new GetGenreResponse(
                genre.getId(),
                genre.getName()
        );
    }
}
