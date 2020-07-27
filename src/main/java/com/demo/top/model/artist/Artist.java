package com.demo.top.model.artist;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Artist {

    public Artist() { }

    private Artist(ArtistBuilder artistBuilder) {
        this.amgArtistId = artistBuilder.amgArtistId;
        this.artistId = artistBuilder.artistId;
        this.artistName = artistBuilder.artistName;
    }

    private String artistName;
    // Might be unnecessary
    private Long artistId;

    @Id
    private Long amgArtistId;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public Long getAmgArtistId() {
        return amgArtistId;
    }

    public void setAmgArtistId(Long amgArtistId) {
        this.amgArtistId = amgArtistId;
    }

    public static class ArtistBuilder {
        private String artistName;
        private Long artistId;
        private Long amgArtistId;

        public ArtistBuilder artistName(String artistName) {
            this.artistName = artistName;
            return this;
        }

        public ArtistBuilder artistId(Long artistId) {
            this.artistId = artistId;
            return this;
        }

        public ArtistBuilder amgArtistId(Long amgArtistId) {
            this.amgArtistId = amgArtistId;
            return this;
        }

        public Artist build() {
            return new Artist(this);
        }

    }
}
