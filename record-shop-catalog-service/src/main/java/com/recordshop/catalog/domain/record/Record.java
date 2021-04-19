package com.recordshop.catalog.domain.record;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.recordshop.catalog.domain.artist.Artist;
import com.recordshop.catalog.domain.genre.Genre;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="record")
public class Record {
    public enum RecordState { ACTIVE, DELETED }

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

    @Column(name="state", nullable = false)
    private RecordState state;

    public void delete() {
        state = RecordState.DELETED;
    }

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

    public void update(
            String title,
            String album,
            BigDecimal price,
            List<Artist> artists,
            List<Genre> genres
    ) {
        if (title != null)
            this.title = title;
        if (album != null)
            this.album = album;
        if (price != null)
            this.price = price;
        if (artists != null)
            this.artists = artists;
        if (genres != null)
            this.genres = genres;
    }
}
