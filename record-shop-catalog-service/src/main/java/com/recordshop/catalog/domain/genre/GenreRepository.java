package com.recordshop.catalog.domain.genre;

import com.recordshop.catalog.domain.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
