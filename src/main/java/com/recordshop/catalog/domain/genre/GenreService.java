package com.recordshop.catalog.domain.genre;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import com.recordshop.catalog.web.genre.CreateGenreRequest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreService {
	
	private final GenreRepository genreRepository;
	
	public Genre create(CreateGenreRequest request) {
    	
		Genre genre = Genre.builder()
    			
    			.id(null)
    			.name(request.getName())
    			.build();
    	
    	return genreRepository.save(genre);
	}
	
	public Genre update(@NonNull Long id, CreateGenreRequest request) {
    	
		Genre genre = genreRepository.findById(id)
				.orElseThrow(EntityNotFoundException::new);

		genre.update(
				request.getName()
		);
    	
    	return genreRepository.save(genre);

	}
	
	public List<Genre> getGenres() {
		return genreRepository.findAll();
	}
	
	public Optional<Genre> findById(Long genreId) {
		return genreRepository.findById(genreId);
	}

	public List<Genre> findGenres(List<Long> genreIds) {
		if (genreIds == null) return null;
		List<Genre> genres = genreRepository.findAllById(genreIds);
		if (genres.size() < genreIds.size())
			throw new EntityNotFoundException("One of the genres does not exist");
		return genres;
	}
}
