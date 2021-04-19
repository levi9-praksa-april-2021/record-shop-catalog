package com.recordshop.catalog.web.artist;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recordshop.catalog.domain.artist.Artist;
import com.recordshop.catalog.domain.artist.ArtistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value="/artists", produces= MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ArtistController {	
	
	private final ArtistService artistService ;
	private final ArtistMapper artistMapper;
	
	@GetMapping("/{artistId}")
	public ResponseEntity<ArtistDTO> getArtist(@PathVariable Long artistId) {
		return artistService.findById(artistId)
				.map(artist -> ResponseEntity.ok(makeGetArtistResponse(artist)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("")
	public ResponseEntity<List<ArtistDTO>> getArtists() {
		return ResponseEntity.ok(artistMapper.map(artistService.getArtists()));
	}
	
	@PostMapping("")
	public ResponseEntity<ArtistDTO> createArtist(@RequestBody CreateArtistRequest request)  {
		Artist artist = artistService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(artistMapper.toDto(artist));
	}
	
	@PutMapping("/{artistId}")
	public ResponseEntity<ArtistDTO> updateArtist(@PathVariable Long artistId, @RequestBody CreateArtistRequest request)  {
		Artist artist = artistService.update(artistId, request);
        return ResponseEntity.status(HttpStatus.OK).body(artistMapper.toDto(artist));
	}
	
	private ArtistDTO makeGetArtistResponse(Artist artist) {
        return artistMapper.toDto(artist);
    }
	
}
