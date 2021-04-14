package com.recordshop.catalog.domain.record;

import com.recordshop.catalog.domain.artist.Artist;
import com.recordshop.catalog.domain.genre.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="record")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", unique = true, nullable = false)
    private String title;

    @Column(name="album", nullable = false)
    private String album;

    @Column(name="price", nullable = false)
    private BigDecimal price;

    @Column(name="stock", nullable = false)
    private int stock;

    @Column(name="archived", nullable = false)
    private boolean archived;

    @ManyToMany
    @JoinTable(name = "record_artist",
            joinColumns = @JoinColumn(name = "record_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Artist> artists;

    public void addArtist(Artist artist) {
        artists.add(artist);
    }

    @ManyToMany
    @JoinTable(name = "record_genre",
            joinColumns = @JoinColumn(name = "record_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    public void addGenre(Genre genre) {
        genres.add(genre);
    }
}
