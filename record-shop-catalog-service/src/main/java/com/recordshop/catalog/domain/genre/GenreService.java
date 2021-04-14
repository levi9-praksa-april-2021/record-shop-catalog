package com.recordshop.catalog.domain.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.recordshop.catalog.web.genre.GenreDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreService {
	
	@Autowired
	public GenreRepository genreRepository;
	
	public GenreDTO save(GenreDTO genreDTO) {
    	
    	Genre genre = new Genre();
    	
    	boolean isUpdate = genreDTO.getId() != null;
    	if (isUpdate) {
			genre = genreRepository.getOne(genreDTO.getId());
			
		} else {
			genre.setId(null);
		}	
    	genre.setName(genreDTO.getName());
    	
    	genreRepository.saveAndFlush(genre);
    	
    	genreDTO.setId(genre.getId());
    	
    	return genreDTO;
	}
}
