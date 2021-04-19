package com.recordshop.catalog.web.genre;

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
import com.recordshop.catalog.domain.genre.Genre;
import com.recordshop.catalog.domain.genre.GenreService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value="/genres", produces= MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GenreController {
	
	private final GenreService genreService;
	private final GenreMapper genreMapper;
	
	@GetMapping("/{genreId}")
	public ResponseEntity<GenreDTO> getGenre(@PathVariable Long genreId) {
		return genreService.findById(genreId)
				.map(genre -> ResponseEntity.ok(makeGetGenreResponse(genre)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("")
	public ResponseEntity<List<GenreDTO>> getGenres() {
		return ResponseEntity.ok(genreMapper.map(genreService.getGenres()));
	}
	
	@PostMapping("")
	public ResponseEntity<GenreDTO> createGenre(@RequestBody CreateGenreRequest request)  {
		Genre genre = genreService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreMapper.toDto(genre));
	}
	
	@PutMapping("/{genreId}")
	public ResponseEntity<GenreDTO> updateGenre(@PathVariable Long genreId, @RequestBody CreateGenreRequest request)  {
		Genre genre = genreService.update(genreId, request);
        return ResponseEntity.status(HttpStatus.OK).body(genreMapper.toDto(genre));
	}
	
	private GenreDTO makeGetGenreResponse(Genre genre) {
        return genreMapper.toDto(genre);
    }
}
