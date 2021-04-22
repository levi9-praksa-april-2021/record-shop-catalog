package com.recordshop.catalog;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.recordshop.catalog.domain.artist.ArtistRepository;
import com.recordshop.catalog.domain.genre.GenreRepository;
import com.recordshop.catalog.domain.record.RecordRepository;

@SpringBootApplication
@EnableEurekaClient
public class RecordShopCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordShopCatalogServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(RecordRepository recordRepository, GenreRepository genreRepository, ArtistRepository artistRepository) {
		return args -> {
//			Artist a = new Artist(null, "a", "a");
//			a = artistRepository.save(a);
//
//			Genre g = new Genre(null, "g");
//			g = genreRepository.save(g);
//
//			Record r = new Record(null, "r", "alb", BigDecimal.valueOf(300.0), 10, Record.RecordState.ACTIVE, new ArrayList<>(), new ArrayList<>());
//			r.addArtist(a);
//			r.addGenre(g);
//
//			r = recordRepository.save(r);
//
//			Map<String, String> propertyPathMapper = new HashMap<>();
//			propertyPathMapper.put("album", "record.album");
//
//
//			int size = recordRepository.findAll(toSpecification("album=='alb';genres.name=='a'")).size();
//			System.out.println(size);
		};
	}
}
