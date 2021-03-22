package com.demo.top.model.artist;

import java.util.List;

public class Artist {

    public Artist() { }

    private Artist(ArtistBuilder artistBuilder) {
        this.name = artistBuilder.name;
        this.id = artistBuilder.id;
        this.popularity = artistBuilder.popularity;
    }

    private String name;
    // Might be unnecessary
    private String id;

    private List<String> genres;

    private Long popularity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPopularity() {
        return popularity;
    }

    public void setPopularity(Long popularity) {
        this.popularity = popularity;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public static class ArtistBuilder {
        private String name;
        private String id;
        private Long popularity;

        public ArtistBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ArtistBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ArtistBuilder popularity(Long popularity) {
            this.popularity = popularity;
            return this;
        }

        public Artist build() {
            return new Artist(this);
        }

    }
}
