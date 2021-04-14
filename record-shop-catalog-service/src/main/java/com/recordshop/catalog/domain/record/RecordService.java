package com.recordshop.catalog.domain.record;

import com.recordshop.catalog.domain.artist.ArtistRepository;
import com.recordshop.catalog.domain.genre.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;

    public Optional<Record> findById(Long recordId) {
        return recordRepository.findById(recordId);
    }

    public List<Record> getRecords(RecordSearch recordSearch) {
        return recordRepository.searchRecords(
                recordSearch.getAlbum(),
                recordSearch.getGenre(),
                recordSearch.getArtist());
    }
}
