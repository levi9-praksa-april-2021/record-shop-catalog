package com.recordshop.catalog.domain.record;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.recordshop.catalog.domain.artist.Artist;
import com.recordshop.catalog.domain.artist.ArtistRepository;
import com.recordshop.catalog.domain.genre.Genre;
import com.recordshop.catalog.domain.genre.GenreRepository;
import com.recordshop.catalog.web.artist.ArtistDTO;
import com.recordshop.catalog.web.genre.GenreDTO;
import com.recordshop.catalog.web.record.RecordDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.InvalidDataAccessApiUsageException;



import static io.github.perplexhub.rsql.RSQLJPASupport.*;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;

    public Optional<Record> findById(Long recordId) {
        return recordRepository.findById(recordId);
    }

    public List<Record> getRecords(String filter) throws InvalidRecordFilterException {
        try {
            return recordRepository.findAll(toSpecification(filter));
        } catch (InvalidDataAccessApiUsageException e) {
            throw new InvalidRecordFilterException("Filter invalid: " + filter);
        }
    }
    
    public RecordDTO save(RecordDTO recordDTO) {
    	
    	Record record = new Record();
    	
    	boolean isUpdate = recordDTO.getId() != null;
		if (isUpdate) {
			record = recordRepository.getOne(recordDTO.getId());
			
		} else {
			record.setId(null);
			record.setArchived(false);
		}
		
		record.setTitle(recordDTO.getTitle());
		record.setAlbum(recordDTO.getAlbum());
		record.setPrice(recordDTO.getPrice());
		record.setStock(recordDTO.getStock());
		
		
		if (isUpdate) {
			for (Artist artist : new ArrayList<>(record.getArtists())) {
				record.getArtists().remove(artist);
			}
		}
		if (recordDTO.getArtists() != null) {
			List<Artist> artists = new ArrayList<>();
			for (ArtistDTO artistDTO : recordDTO.getArtists()) {
				Artist artist = artistRepository.getOne(artistDTO.getId());
				
				artists.add(artist);
			}
			record.setArtists(artists);
		}
		
		if (isUpdate) {
			for (Genre genre : new ArrayList<>(record.getGenres())) {
				record.getGenres().remove(genre);
			}
		}
		if (recordDTO.getGenres() != null) {
			List<Genre> genres = new ArrayList<>();
			for (GenreDTO genreDTO : recordDTO.getGenres()) {
				Genre genre = genreRepository.getOne(genreDTO.getId());
				
				genres.add(genre);
			}
			record.setGenres(genres);
		}
		
		recordRepository.saveAndFlush(record);
		
		recordDTO.setId(record.getId());
		
		return recordDTO;
	}
    
    public void delete(Long id) {
    	
    	if(id != null) {
    		Optional<Record> recordOpt = recordRepository.findById(id);
    		if (recordOpt.isPresent()) {
    			Record record = recordOpt.get();
    			record.setArchived(true);
    			recordRepository.save(record);
    		}
    	}
    	
    	
    }
    
}
