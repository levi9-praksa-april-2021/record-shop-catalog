package com.recordshop.catalog.web.genre;

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

import com.recordshop.catalog.domain.genre.Genre;
import com.recordshop.catalog.domain.genre.GenreService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path="/genres")
@RequiredArgsConstructor
public class GenreController {
	
	public final GenreService genreService;
	private GenreMapper genreMapper = Mappers.getMapper(GenreMapper.class);
	
	@PostMapping(value = "/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<GenreDTO> saveGenre(@RequestBody GenreDTO genreDTO)  {
		return ResponseEntity.ok(genreService.save(genreDTO));
	}
	
	@GetMapping(path = "")
	public ResponseEntity<List<GenreDTO>> getGenres() {
		return ResponseEntity.ok(genreMapper.map(genreService.getGenres()));
	}
	
	@GetMapping(path = "/{genreId}")
	public ResponseEntity<GenreDTO> getGenre(@PathVariable Long genreId) {
		return genreService.findById(genreId)
				.map(genre -> ResponseEntity.ok(makeGetGenreResponse(genre)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	private GenreDTO makeGetGenreResponse(Genre genre) {
        return genreMapper.toDto(genre);
    }
}
