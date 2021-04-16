package com.recordshop.catalog.domain.artist;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import com.recordshop.catalog.web.artist.CreateArtistRequest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistService {
	
	
	private final ArtistRepository artistRepository;
	
	public Artist create(CreateArtistRequest request) {
    	
    	Artist artist = Artist.builder()
    			
    			.id(null)
    			.firstName(request.getFirstName())
    			.lastName(request.getLastName())
    			.build();
    	
    	return artistRepository.save(artist);
	}
	
	public Artist update(@NonNull Long id, CreateArtistRequest request) {
    	
		Artist artist = artistRepository.findById(id)
				.orElseThrow(EntityNotFoundException::new);

		artist.update(
				request.getFirstName(),
				request.getLastName()
		);
    	
    	return artistRepository.save(artist);

	}
	
	public List<Artist> getArtists() {
		return artistRepository.findAll();
	}
	
	public Optional<Artist> findById(Long artistId) {
		return artistRepository.findById(artistId);
	}
}
