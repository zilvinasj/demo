package com.demo.top.model.response;

import com.demo.top.model.artist.Artist;
import java.util.List;

public class ApplicationArtistResponse {
    private List<Artist> artists;

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
