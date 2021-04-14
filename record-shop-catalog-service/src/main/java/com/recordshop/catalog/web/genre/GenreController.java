package com.recordshop.catalog.web.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recordshop.catalog.domain.genre.GenreService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path="/genres")
@RequiredArgsConstructor
public class GenreController {
	
	@Autowired
	public GenreService genreService;
	
	@PostMapping(value = "/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<GenreDTO> saveGenre(@RequestBody GenreDTO genreDTO)  {
		return ResponseEntity.ok(genreService.save(genreDTO));
	}
	
}
