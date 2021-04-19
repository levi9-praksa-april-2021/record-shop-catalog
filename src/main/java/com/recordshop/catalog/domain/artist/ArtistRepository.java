package com.recordshop.catalog.domain.artist;

import com.recordshop.catalog.domain.artist.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
