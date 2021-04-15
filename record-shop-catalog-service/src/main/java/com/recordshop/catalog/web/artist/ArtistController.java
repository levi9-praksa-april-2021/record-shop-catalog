package com.recordshop.catalog.web.artist;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.recordshop.catalog.domain.artist.Artist;
import com.recordshop.catalog.domain.artist.ArtistService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path="/artists")
@RequiredArgsConstructor
public class ArtistController {
	
	
	private final ArtistService artistService ;

	private ArtistMapper artistMapper = Mappers.getMapper(ArtistMapper.class);
	
	@PostMapping(value = "/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<ArtistDTO> saveArtist(@RequestBody ArtistDTO artistDTO)  {
		return ResponseEntity.ok(artistService.save(artistDTO));
	}
	
	@GetMapping(path = "")
	public ResponseEntity<List<ArtistDTO>> getArtists() {
		return ResponseEntity.ok(artistMapper.map(artistService.getArtists()));
	}
	
	@GetMapping(path = "/{artistId}")
	public ResponseEntity<ArtistDTO> getArtist(@PathVariable Long artistId) {
		return artistService.findById(artistId)
				.map(artist -> ResponseEntity.ok(makeGetArtistResponse(artist)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	private ArtistDTO makeGetArtistResponse(Artist artist) {
        return artistMapper.toDto(artist);
    }
	
}
