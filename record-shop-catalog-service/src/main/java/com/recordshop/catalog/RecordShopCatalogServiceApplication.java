package com.recordshop.catalog;

import com.recordshop.catalog.domain.*;
import com.recordshop.catalog.domain.Record;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.security.Security;
import java.util.ArrayList;

@SpringBootApplication
public class RecordShopCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordShopCatalogServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(RecordRepository recordRepository, GenreRepository genreRepository, ArtistRepository artistRepository) {
		return args -> {
			Artist a = new Artist(null, "a", "a");
			a = artistRepository.save(a);

			Genre g = new Genre(null, "g");
			g = genreRepository.save(g);

			Record r = new Record(null, "r", "alb", BigDecimal.valueOf(300.0), 10, false, new ArrayList<>(), new ArrayList<>());
			r.addArtist(a);
			r.addGenre(g);

			r = recordRepository.save(r);
		};
	}
}
