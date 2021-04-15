package com.recordshop.catalog.domain.artist;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.recordshop.catalog.web.artist.ArtistDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistService {
	
	@Autowired
	private ArtistRepository artistRepository;
	
	public ArtistDTO save(ArtistDTO artistDTO) {
    	
    	Artist artist = new Artist();
    	
    	boolean isUpdate = artistDTO.getId() != null;
		if (isUpdate) {
			artist = artistRepository.getOne(artistDTO.getId());
			
		} else {
			artist.setId(null);
		}
    	artist.setFirstName(artistDTO.getFirstName());
    	artist.setLastName(artistDTO.getLastName());
    	
    	artistRepository.saveAndFlush(artist);
    	
    	artistDTO.setId(artist.getId());
    	
    	return artistDTO;
	}
	
	public List<Artist> getArtists() {
		return artistRepository.findAll();
	}
	
	public Optional<Artist> findById(Long artistId) {
		return artistRepository.findById(artistId);
	}
}
