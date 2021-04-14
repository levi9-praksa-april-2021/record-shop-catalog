package com.recordshop.catalog.web.artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recordshop.catalog.domain.artist.ArtistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path="/artists")
@RequiredArgsConstructor
public class ArtistController {
	
	@Autowired
	private ArtistService artistService;
	
	@PostMapping(value = "/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<ArtistDTO> saveArtist(@RequestBody ArtistDTO artistDTO)  {
		return ResponseEntity.ok(artistService.save(artistDTO));
	}
}
