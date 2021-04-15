package com.recordshop.catalog.domain.record;

import java.util.List;
import java.util.Optional;

import com.recordshop.catalog.web.record.CreateRecordRequest;
import com.recordshop.catalog.web.record.UpdateRecordRequest;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import com.recordshop.catalog.domain.artist.Artist;
import com.recordshop.catalog.domain.artist.ArtistRepository;
import com.recordshop.catalog.domain.genre.Genre;
import com.recordshop.catalog.domain.genre.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.InvalidDataAccessApiUsageException;


import javax.persistence.EntityNotFoundException;

import static io.github.perplexhub.rsql.RSQLJPASupport.*;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;

    public Optional<Record> findById(@NonNull Long recordId) {
        return recordRepository.findById(recordId);
    }

    public List<Record> getRecords(String filter) throws InvalidRecordFilterException {
        try {
            return recordRepository.findAll(toSpecification(filter));
        } catch (InvalidDataAccessApiUsageException e) {
            throw new InvalidRecordFilterException("Filter invalid: " + filter);
        }
    }

    public Record create(CreateRecordRequest request) {
    	List<Artist> artists = findArtists(request.getArtistIds());
    	List<Genre> genres = findGenres(request.getGenreIds());

     	Record record = Record.builder()
				.id(null)
				.title(request.getTitle())
				.album(request.getAlbum())
				.price(request.getPrice())
				.stock(request.getStock())
				.artists(artists)
				.genres(genres)
				.state(Record.RecordState.ACTIVE)
				.build();

    	return recordRepository.save(record);
	}

	public Record update(@NonNull Long id, UpdateRecordRequest request) {
		Record record = recordRepository.findById(id)
				.orElseThrow(EntityNotFoundException::new);

		List<Artist> artists = findArtists(request.getArtistIds());
		List<Genre> genres = findGenres(request.getGenreIds());

		record.update(
				request.getTitle(),
				request.getAlbum(),
				request.getPrice(),
				artists,
				genres
		);

		return recordRepository.save(record);
	}

	private List<Artist> findArtists(List<Long> artistIds) {
    	if (artistIds == null) return null;
    	List<Artist> artists = artistRepository.findAllById(artistIds);
		if (artists.size() < artistIds.size())
			throw new EntityNotFoundException("One of the artists does not exist");
		return artists;
	}

	private List<Genre> findGenres(List<Long> genreIds) {
    	if (genreIds == null) return null;
    	List<Genre> genres = genreRepository.findAllById(genreIds);
		if (genres.size() < genreIds.size())
			throw new EntityNotFoundException("One of the genres does not exist");
		return genres;
	}
    
    public void delete(@NonNull Long id) {
    	Record record = recordRepository.findById(id)
				.orElseThrow(EntityNotFoundException::new);
    	record.delete();
    	recordRepository.save(record);
    }
}
