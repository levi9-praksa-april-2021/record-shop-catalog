package com.recordshop.catalog.domain.record;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecordSearch {
    private String album;
    private String genre;
    private String artist;

    public RecordSearch withAlbum(String album) {
        if (album != null)
            this.album = "%" + album + "%";
        return this;
    }

    public RecordSearch withGenre(String genre) {
        if (genre != null)
            this.genre = "%" + genre + "%";
        return this;
    }

    public RecordSearch withArtist(String artist) {
        if (artist != null)
            this.artist = "%" + artist + "%";
        return this;
    }
}
